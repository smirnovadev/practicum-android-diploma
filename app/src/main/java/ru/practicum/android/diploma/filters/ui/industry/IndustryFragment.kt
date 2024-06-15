package ru.practicum.android.diploma.filters.ui.industry

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.filters.presentation.IndustryViewModel
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryFragment : Fragment() {
    private var _binding: FragmentIndustryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<IndustryViewModel>()

    private var rvAdapter: IndustryAdapter? = null
    private lateinit var recycler: RecyclerView

    private val industries = mutableListOf<Industry>()
    private var searchDebounce: ((String) -> Unit)? = null
    private lateinit var inputIndustry: TextInputEditText
    private lateinit var groupNotFound: Group
    private lateinit var groupEmpty: Group
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        inputIndustry = binding.inputIndustry
        groupNotFound = binding.groupNotFound
        groupEmpty = binding.groupEmpty
        groupNotFound.visibility = View.GONE
        groupEmpty.visibility = View.GONE
        recycler = binding.recyclerView
        rvAdapter = IndustryAdapter(industries) {
            viewModel.save(it)
            findNavController().navigateUp()
        }
        recycler.adapter = rvAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        searchDebounce = debounce<String>(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { request -> viewModel.search(request) }

        inputIndustry.addTextChangedListener(
            onTextChanged = {s: CharSequence?, start: Int, before: Int, count: Int->
                viewModel.search(s?.toString().orEmpty())
            },
            afterTextChanged =
            { editable ->
                if (editable.toString().isNotEmpty()) {
                    search(editable.toString())
                }
            }
        )
        inputIndustry.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                search(inputIndustry.text.toString())
                inputIndustry.clearFocus()
            }
            false
        }
        binding.inputLayoutIndustry.setEndIconOnClickListener {
            inputIndustry.setText(EMPTY)
            val inputMethodManager = requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        }

        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is IndustryState.Content -> showContent(state.industryList)
                IndustryState.Empty -> showEmpty()
                is IndustryState.Error -> showError(state.code)
                IndustryState.Loading -> showLoading()
            }
        }
    }

    private fun search(request: String) {
        searchDebounce?.let { it(request) }
    }

    private fun showContent(industryList: List<Industry>) {
        industries.clear()
        industries.addAll(industryList)
        binding.recyclerView.isVisible = true
        rvAdapter!!.notifyDataSetChanged()
        binding.apply {
            groupEmpty.isVisible = false
            groupNotFound.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showEmpty() {
        binding.apply {
            binding.recyclerView.isVisible = false
            binding.groupEmpty.isVisible = true
            binding.groupNotFound.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showError(code: Int) {
        binding.apply {
            binding.recyclerView.isVisible = false
            binding.groupEmpty.isVisible = false
            binding.groupNotFound.isVisible = true
            progressBar.isVisible = false
        }
    }

    private fun showLoading() {
        binding.apply {
            progressBar.isVisible = true
            binding.recyclerView.isVisible = false
            binding.groupEmpty.isVisible = false
            binding.groupNotFound.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rvAdapter = null
        recycler.adapter = null
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 200L
        private const val EMPTY = ""
    }
}
