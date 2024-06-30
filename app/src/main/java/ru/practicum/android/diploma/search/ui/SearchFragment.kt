package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.filters.domain.models.FiltersState
import ru.practicum.android.diploma.job.ui.JobFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.SearchViewModel
import ru.practicum.android.diploma.search.ui.adapter.SearchAdapter
import ru.practicum.android.diploma.search.ui.adapter.SearchClickListener

class SearchFragment : Fragment(), SearchClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private var textWatcher: TextWatcher? = null
    private val searchAdapter = SearchAdapter(this)
    private var clickDebounce: ((Boolean) -> Unit)? = null
    private var isClickAllowed = true
    private var uploadDebounce: ((Boolean) -> Unit)? = null
    private var isUploadingAllowed = true
    private var stateHandler: SearchFragmentStateHandler? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        stateHandler = SearchFragmentStateHandler(binding, searchAdapter, requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenState().observe(viewLifecycleOwner) { state ->
            stateHandler?.renderState(state)
        }

        viewModel.getFiltersState().observe(viewLifecycleOwner) { state ->
            renderFiltersState(state)
        }

        binding.apply {
            recyclerView.adapter = searchAdapter
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        clickDebounce = debounce(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { isClickAllowed = true }

        uploadDebounce = debounce(
            UPLOAD_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { isUploadingAllowed = true }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val pos = (binding.recyclerView.layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()
                    val itemsCount = searchAdapter.itemCount
                    if (pos >= itemsCount - 1) {
                        if (isUploadingAllowed) {
                            isUploadingAllowed = false
                            viewModel.uploadPage()
                            uploadDebounce?.let { it(isUploadingAllowed) }
                        }
                    }
                }
            }
        })

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_close_black)
                } else {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_search_black)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    viewModel.searchDebounce(s.toString())
                }
            }
        }

        textWatcher.let { binding.searchField.addTextChangedListener(it) }

        binding.searchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (binding.searchField.text.isNotEmpty()) {
                    viewModel.actionDoneRequest(binding.searchField.text.toString())
                }
            }
            false
        }

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

    private fun renderFiltersState(state: FiltersState) {
        when (state) {
            FiltersState.Active -> binding.icFilter.setImageResource(R.drawable.ic_filter_on)
            FiltersState.Inactive -> binding.icFilter.setImageResource(R.drawable.ic_filter_off)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkFiltersStatus()
        isClickAllowed = true
        isUploadingAllowed = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher.let { binding.searchField.removeTextChangedListener(it) }
        _binding = null
        clickDebounce = null
        stateHandler = null
    }

    override fun onPause() {
        super.onPause()
        viewModel.prepareOnResumeState()
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        if (isClickAllowed) {
            isClickAllowed = false
            clickDebounce?.let { it(isClickAllowed) }
            navigateToJobFragment(vacancy.id)
        }
    }

    private fun navigateToJobFragment(vacancyId: String) {
        findNavController().navigate(
            R.id.action_searchFragment_to_jobFragment,
            JobFragment.createArgs(
                vacancyId = vacancyId
            )
        )
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
        private const val UPLOAD_DEBOUNCE_DELAY = 500L
        const val EXTRA_ID = "vacancy_id"
    }
}
