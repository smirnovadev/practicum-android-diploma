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

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_close)
                } else {
                    binding.searchFieldIcon.setImageResource(R.drawable.ic_search)
                }
            }
        }

        textWatcher.let { binding.searchField.addTextChangedListener(it) }

        binding.searchFieldIcon.setOnClickListener {
            binding.searchField.setText("")
            viewModel.clearSearchField()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher.let { binding.searchField.removeTextChangedListener(it) }
        _binding = null
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Default -> {
                binding.searchScreenCover.isVisible = true
                binding.progressBar.isVisible = false
                binding.errorPlaceholder.isVisible = false
                binding.searchStatus.isVisible = false
                binding.recyclerView.isVisible = false
            }

            is SearchScreenState.Loading -> {
                showProgressbar()
            }

            is SearchScreenState.InternetConnectionError -> {
                showInternetConnectionError()
            }

            is SearchScreenState.ServerError -> {
                showServerError()
            }

            is SearchScreenState.SearchError -> {
                showSearchError()
            }
        }
    }

    private fun showProgressbar() {
        binding.searchScreenCover.isVisible = false
        binding.progressBar.isVisible = true
        binding.errorPlaceholder.isVisible = false
        binding.searchStatus.isVisible = false
        binding.recyclerView.isVisible = false
    }

    private fun showInternetConnectionError() {
        binding.searchScreenCover.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorPlaceholder.isVisible = true
        binding.errorPlaceholder.setText(R.string.no_internet)
        binding.errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.img_internet_connection_error,
            0,
            0
        )
        binding.searchStatus.isVisible = false
        binding.recyclerView.isVisible = false
    }

    private fun showServerError() {
        binding.searchScreenCover.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorPlaceholder.isVisible = true
        binding.errorPlaceholder.setText(R.string.server_error)
        binding.errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.img_server_error_placeholder,
            0,
            0
        )
        binding.searchStatus.isVisible = false
        binding.recyclerView.isVisible = false
    }

    private fun showSearchError() {
        binding.searchScreenCover.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorPlaceholder.isVisible = true
        binding.errorPlaceholder.setText(R.string.empty_search_results)
        binding.errorPlaceholder.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.img_empty_search_results,
            0,
            0
        )
        binding.searchStatus.isVisible = true
        binding.searchStatus.text = getString(R.string.no_such_vacancies)
        binding.recyclerView.isVisible = false
    }
}
