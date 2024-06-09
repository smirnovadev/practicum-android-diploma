package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.SearchScreenState
import ru.practicum.android.diploma.search.presentation.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private var textWatcher: TextWatcher? = null

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

        binding.searchFieldIcon.setOnClickListener {
            binding.editText.setText("")
            viewModel.clearSearchField()
        }

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_close)
                } else {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_search)
                }
            }
        }

        textWatcher.let { binding.editText.addTextChangedListener(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher.let { binding.editText.removeTextChangedListener(it) }
        _binding = null
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> {
                binding.searchScreenCover.isVisible = true
                binding.progressBar.isVisible = false
                binding.noInternetPlaceholder.isVisible = false
                binding.emptySearchResultsPlaceholder.isVisible = false
                binding.serverErrorPlaceholder.isVisible = false
                binding.searchStatus.isVisible = false
                binding.recyclerView.isVisible = false
            }

            is SearchScreenState.Loading -> {
                binding.searchScreenCover.isVisible = false
                binding.progressBar.isVisible = true
                binding.noInternetPlaceholder.isVisible = false
                binding.emptySearchResultsPlaceholder.isVisible = false
                binding.serverErrorPlaceholder.isVisible = false
                binding.searchStatus.isVisible = false
                binding.recyclerView.isVisible = false
            }

            is SearchScreenState.InternetConnectionError -> {
                binding.searchScreenCover.isVisible = false
                binding.progressBar.isVisible = false
                binding.noInternetPlaceholder.isVisible = true
                binding.emptySearchResultsPlaceholder.isVisible = false
                binding.serverErrorPlaceholder.isVisible = false
                binding.searchStatus.isVisible = false
                binding.recyclerView.isVisible = false
            }

            is SearchScreenState.SearchError -> {
                binding.searchScreenCover.isVisible = false
                binding.progressBar.isVisible = false
                binding.noInternetPlaceholder.isVisible = false
                binding.emptySearchResultsPlaceholder.isVisible = true
                binding.serverErrorPlaceholder.isVisible = false
                binding.searchStatus.isVisible = true
                binding.searchStatus.text = getString(R.string.no_such_vacancies)
                binding.recyclerView.isVisible = false
            }

            is SearchScreenState.ServerError -> {
                binding.searchScreenCover.isVisible = false
                binding.progressBar.isVisible = false
                binding.noInternetPlaceholder.isVisible = false
                binding.emptySearchResultsPlaceholder.isVisible = false
                binding.serverErrorPlaceholder.isVisible = true
                binding.searchStatus.isVisible = false
                binding.recyclerView.isVisible = false
            }
        }
    }
}
