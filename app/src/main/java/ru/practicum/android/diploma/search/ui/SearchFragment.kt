package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.job.ui.JobFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.SearchScreenState
import ru.practicum.android.diploma.search.presentation.SearchViewModel
import ru.practicum.android.diploma.search.ui.adapter.SearchAdapter
import ru.practicum.android.diploma.search.ui.adapter.SearchClickListener
import ru.practicum.android.diploma.util.Formatter

class SearchFragment : Fragment(), SearchClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private var textWatcher: TextWatcher? = null
    private var searchAdapter = SearchAdapter(this)
    private var clickDebounce: ((Boolean) -> Unit)? = null
    private var isClickAllowed = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenState().observe(viewLifecycleOwner) { state ->
            renderState(state)
        }

        binding.apply {
            recyclerView.adapter = searchAdapter
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        clickDebounce = debounce<Boolean>(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { if (!isClickAllowed) allowClick() }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val pos = (binding.recyclerView.layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()
                    val itemsCount = searchAdapter.itemCount
                    if (pos >= itemsCount - 1) {
                        viewModel.uploadPage()
                    }
                }
            }
        })

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_close)
                } else {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_search)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    viewModel.searchDebounce(s.toString())
                }
            }
        }

        textWatcher.let { binding.searchField.addTextChangedListener(it) }

        binding.searchFieldIcon.setOnClickListener {
            binding.searchField.setText("")
            searchAdapter.vacanciesList.clear()
            searchAdapter.notifyDataSetChanged()
            viewModel.clearSearchField()
        }

        binding.icFilter.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher.let { binding.searchField.removeTextChangedListener(it) }
        _binding = null
        clickDebounce = null
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> showDefaultScreenState()
            is SearchScreenState.Loading -> showProgressbar()
            is SearchScreenState.InternetConnectionError -> showInternetConnectionError()
            is SearchScreenState.ServerError -> showServerError()
            is SearchScreenState.SearchError -> showSearchError()
            is SearchScreenState.ShowContent -> showContent(state.vacancies, state.found)
            is SearchScreenState.UploadNextPage -> uploadNextPage()
            is SearchScreenState.Error -> showError()
            is SearchScreenState.IOError -> showIOError()
        }
    }

    private fun showProgressbar() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = true
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showInternetConnectionError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.no_internet)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_internet_connection_error,
                0,
                0
            )
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showServerError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.server_error)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_server_error_placeholder,
                0,
                0
            )
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showSearchError() {
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = true
            errorPlaceholder.setText(R.string.empty_search_results)
            errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_empty_search_results,
                0,
                0
            )
            searchStatus.isVisible = true
            searchStatus.text = getString(R.string.no_such_vacancies)
            recyclerView.isVisible = false
        }
    }

    private fun showDefaultScreenState() {
        binding.apply {
            searchScreenCover.isVisible = true
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun uploadNextPage() {
        binding.apply {
            errorPlaceholder.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), getString(R.string.error_occured), Toast.LENGTH_SHORT).show()
        binding.apply {
            searchScreenCover.isVisible = true
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showIOError() {
        Toast.makeText(requireContext(), getString(R.string.error_occured), Toast.LENGTH_SHORT).show()
        binding.apply {
            searchScreenCover.isVisible = false
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = true
            recyclerView.isVisible = true
        }
    }

    private fun showContent(list: ArrayList<Vacancy>, resultsQty: Int) {
        searchAdapter.vacanciesList.clear()
        searchAdapter.vacanciesList.addAll(list)
        searchAdapter.notifyDataSetChanged()
        binding.apply {
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = true
            searchStatus.text = getVacanciesWordForm(resultsQty)
            recyclerView.isVisible = true
        }
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        isClickAllowed = false
        navigateToJobFragment(vacancy.id)
        clickDebounce?.let { it(isClickAllowed) }
    }

    private fun navigateToJobFragment(vacancyId: String) {
        findNavController().navigate(
            R.id.action_searchFragment_to_jobFragment,
            JobFragment.createArgs(
                vacancyId
            )
        )
    }

    private fun getVacanciesWordForm(quantity: Int): String {
        val vacancyForm = Formatter
            .quantityWordFormFormatter(
                quantity,
                getString(R.string.vacancy),
                getString(R.string.vacancy_genitive),
                getString(R.string.vacancies_genitive)
            )

        val foundForm = Formatter
            .quantityWordFormFormatter(
                quantity,
                getString(R.string.found_f),
                getString(R.string.found_f_genitive),
                getString(R.string.found_f_genitive_pl)
            )

        return "$foundForm $quantity $vacancyForm"
    }

    private fun allowClick() {
        isClickAllowed = true
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
        const val EXTRA_ID = "vacancy_id"

    }
}
