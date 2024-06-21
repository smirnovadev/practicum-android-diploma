package ru.practicum.android.diploma.filters.ui.area

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
import ru.practicum.android.diploma.databinding.FragmentSelectRegionBinding
import ru.practicum.android.diploma.filters.presentation.RegionViewModel
import ru.practicum.android.diploma.search.domain.model.fields.Area

class RegionFragment : Fragment() {
    private var _binding: FragmentSelectRegionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<RegionViewModel>()

    private var rvAdapter: AreaAdapter? = null

    private val regions = mutableListOf<Area>()
    private var searchDebounce: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.groupNotFound.visibility = View.GONE
        binding.groupEmpty.visibility = View.GONE
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        searchDebounce = debounce(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { request -> viewModel.search(request) }
        rvAdapter = AreaAdapter(regions) {
            viewModel.save(it)
            findNavController().navigateUp()
        }
        binding.recyclerView.adapter = rvAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        initListeners()
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is AreasState.Content -> showContent(state.areasList)
                AreasState.Empty -> showEmpty()
                is AreasState.Error -> showError()
                AreasState.Loading -> showLoading()
            }
        }
    }

    private fun initListeners() {
        binding.inputRegion.addTextChangedListener(
            afterTextChanged =
            { editable ->
                if (editable.toString().isNotEmpty()) {
                    search(editable.toString())
                }
            }
        )
        binding.inputRegion.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                search(binding.inputRegion.text.toString())
                binding.inputRegion.clearFocus()
            }
            false
        }

        binding.inputLayoutRegion.setEndIconOnClickListener {
            binding.inputRegion.setText(EMPTY)
            val inputMethodManager = requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun search(request: String) {
        searchDebounce?.let { it(request) }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(countriesList: List<Area>) {
        regions.clear()
        regions.addAll(countriesList)
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

    private fun showError() {
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
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 200L
        private const val EMPTY = ""
    }
}
