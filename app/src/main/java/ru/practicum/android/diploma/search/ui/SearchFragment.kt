package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    private var searchDebounce: ((String) -> Unit)? = null
    private var searchAdapter: SearchAdapter? = null

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

        searchDebounce = debounce<String>(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { request -> viewModel.search(request) }

        searchAdapter = SearchAdapter(this)
        binding.apply {
            recyclerView.adapter = searchAdapter
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

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
                    search(s.toString())
                }
            }
        }

        textWatcher.let { binding.searchField.addTextChangedListener(it) }

        binding.searchFieldIcon.setOnClickListener {
            binding.searchField.setText("")
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
        searchAdapter = null
        searchDebounce = null
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> {
                showDefaultScreenState()
            }

            is SearchScreenState.Loading -> showProgressbar()
            is SearchScreenState.InternetConnectionError -> showInternetConnectionError()
            is SearchScreenState.ServerError -> showServerError()
            is SearchScreenState.SearchError -> showSearchError()
            is SearchScreenState.ShowContent -> showContent(state.vacancies, state.found)
            is SearchScreenState.uploadNextPage -> showProgressbar()
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

    private fun showContent(list: ArrayList<Vacancy>, resultsQty: Int) {
        searchAdapter?.vacanciesList?.clear()
        searchAdapter?.vacanciesList?.addAll(list)
        searchAdapter?.notifyDataSetChanged()
        binding.apply {
            progressBar.isVisible = false
            errorPlaceholder.isVisible = false
            searchStatus.isVisible = true
            searchStatus.text = getVacanciesWordForm(resultsQty)
            recyclerView.isVisible = true
        }
    }

    private fun search(request: String) {
        searchDebounce?.let { it(request) }
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        findNavController().navigate(
            R.id.action_searchFragment_to_jobFragment,
            JobFragment.createArgs(
                vacancy.id
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

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 3000L
        private const val CLICK_DEBOUNCE_DELAY = 1000L
        const val EXTRA_ID = "vacancy_id"

    }
}
