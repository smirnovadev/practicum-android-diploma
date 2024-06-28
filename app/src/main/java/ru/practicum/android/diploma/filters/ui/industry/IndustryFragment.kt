package ru.practicum.android.diploma.filters.ui.industry

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.filters.domain.state.IndustryState
import ru.practicum.android.diploma.filters.presentation.IndustryViewModel
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryFragment : Fragment() {
    private var _binding: FragmentIndustryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<IndustryViewModel>()

    private var rvAdapter: IndustryAdapter? = null

    private val industries = mutableListOf<Industry>()
    private var searchDebounce: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
            clearIndustry()
        }
        binding.groupNotFound.visibility = View.GONE
        binding.groupError.visibility = View.GONE
        rvAdapter = IndustryAdapter(industries) {
            viewModel.save(it)
            binding.buttonApply.isVisible = true
        }
        binding.recyclerView.adapter = rvAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchDebounce = debounce(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            true
        ) { request -> viewModel.search(request) }

        initListeners()

        binding.buttonApply.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initListeners() {
        binding.inputIndustry.addTextChangedListener(
            onTextChanged = { s: CharSequence?, _: Int, _: Int, _: Int ->
                viewModel.search(s?.toString().orEmpty())
            },
            afterTextChanged =
            { editable ->
                if (editable.toString().isNotEmpty()) {
                    search(editable.toString())
                }
            }
        )
        binding.inputIndustry.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                search(binding.inputIndustry.text.toString())
                binding.inputIndustry.clearFocus()
            }
            false
        }
        binding.inputLayoutIndustry.setEndIconOnClickListener {
            binding.inputIndustry.setText(EMPTY)
            val inputMethodManager = requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        }

        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is IndustryState.Content -> {
                    showContent(state.industryList)
                }

                is IndustryState.Empty -> {
                    showEmpty()
                }

                is IndustryState.Error -> {
                    showError()
                }

                is IndustryState.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun search(request: String) {
        searchDebounce?.let { it(request) }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(industryList: List<Industry>) {
        industries.clear()
        industries.addAll(industryList)

        val industry = viewModel.getIndustry()
        val selectedId = industries.indexOfFirst { it == industry }
        rvAdapter!!.selectedId = if (selectedId != -1) selectedId else null

        binding.recyclerView.isVisible = true
        rvAdapter!!.notifyDataSetChanged()
        binding.apply {
            binding.groupError.isVisible = false
            binding.groupNotFound.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showEmpty() {
        binding.apply {
            recyclerView.isVisible = false
            binding.groupError.isVisible = true
            binding.groupNotFound.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showError() {
        binding.apply {
            recyclerView.isVisible = false
            binding.groupError.isVisible = false
            binding.groupNotFound.isVisible = true
            progressBar.isVisible = false
        }
    }

    private fun showLoading() {
        binding.apply {
            progressBar.isVisible = true
            recyclerView.isVisible = false
            binding.groupError.isVisible = false
            binding.groupNotFound.isVisible = false
        }
    }

    private fun clearIndustry() {
        viewModel.clearIndustry()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rvAdapter = null
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 200L
        private const val EMPTY = ""
    }
}
