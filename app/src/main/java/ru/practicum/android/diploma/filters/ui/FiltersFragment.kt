package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.filters.domain.models.FiltersApplyButtonState
import ru.practicum.android.diploma.filters.domain.models.FiltersResetButtonState
import ru.practicum.android.diploma.filters.domain.models.FiltersScreenState
import ru.practicum.android.diploma.filters.presentation.FiltersViewModel

class FiltersFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FiltersViewModel>()

    private var previousSalary: String = ""
    private var salaryDebounce: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getScreenState().observe(viewLifecycleOwner) { state ->
            renderScreenState(state)
        }

        viewModel.getApplyButtonState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is FiltersApplyButtonState.Visible -> binding.buttonApply.isVisible = true
                is FiltersApplyButtonState.InVisible -> binding.buttonApply.isVisible = false
            }
        }

        viewModel.getResetButtonState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is FiltersResetButtonState.Visible -> binding.reset.isVisible = true
                is FiltersResetButtonState.InVisible -> binding.reset.isVisible = false
            }
        }

        salaryDebounce = debounce(
            DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            true
        ) { newSalary -> viewModel.updateCurrentSalary(newSalary) }

        initListeners()
        buttonsListeners()
    }

    private fun initListeners() {
        binding.placeWorkText.setOnClickListener {
            if (binding.placeWorkText.text?.isEmpty() == true) {
                openPlaceToWorkFragment()
            }
        }

        binding.placeWorkText.addTextChangedListener(
            afterTextChanged = { text: Editable? ->
                if (text.isNullOrEmpty()) {
                    binding.placeWorkBtn.setImageResource(R.drawable.ic_arrow_forward)
                } else {
                    binding.placeWorkBtn.setImageResource(R.drawable.ic_close)
                }
            }
        )

        binding.placeWorkBtn.setOnClickListener {
            if (binding.placeWorkText.text?.isEmpty() == true) {
                openPlaceToWorkFragment()
            } else {
                clearPlaceToWork()
            }
        }

        binding.industryText.setOnClickListener {
            if (binding.industryText.text?.isEmpty() == true) {
                openIndustryFragment()
            }
        }

        binding.industryText.addTextChangedListener(
            afterTextChanged = { text: Editable? ->
                if (text.isNullOrEmpty()) {
                    binding.industryBtn.setImageResource(R.drawable.ic_arrow_forward)
                } else {
                    binding.industryBtn.setImageResource(R.drawable.ic_close)
                }
            }
        )

        binding.industryBtn.setOnClickListener {
            if (binding.industryText.text?.isEmpty() == true) {
                openIndustryFragment()
            } else {
                clearIndustry()
            }
        }

        binding.salaryText
            .addTextChangedListener(
                afterTextChanged = { text: Editable? ->
                    if (text.toString() != previousSalary) {
                        val newSalary = text.toString()
                        salaryDebounce?.let { it(newSalary) }
                        previousSalary = newSalary
                    }
                }
            )

        binding.salaryText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE && !binding.salaryText.text.isNullOrEmpty()) {
                viewModel.updateCurrentSalary(binding.salaryText.text.toString())
            }
            false
        }

        binding.salaryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSalaryFlag(isChecked)
        }
    }

    private fun buttonsListeners() {
        binding.buttonApply.setOnClickListener {
            viewModel.updateCurrentSalary(binding.salaryText.text.toString())
            viewModel.applyFilters()
            findNavController().navigateUp()
        }
        binding.reset.setOnClickListener {
            viewModel.resetFilters()
        }
    }

    private fun clearPlaceToWork() {
        binding.placeWorkText.setText(EMPTY)
        viewModel.clearRegions()
    }

    private fun clearIndustry() {
        binding.industryText.setText(EMPTY)
        viewModel.clearIndustry()
    }

    private fun openIndustryFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_industryFragment)
    }

    private fun openPlaceToWorkFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_placeToWorkFragment)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateFilters()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.updateCurrentSalary(binding.salaryText.text.toString())
        _binding = null
        salaryDebounce = null
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateCurrentSalary(binding.salaryText.text.toString())
    }

    private fun renderScreenState(state: FiltersScreenState) {
        when (state) {
            is FiltersScreenState.Content -> {
                val country = state.currentFilters.country
                val region = state.currentFilters.region
                val col = mutableListOf<String>()
                if (country.isNotEmpty()) {
                    col.add(country)
                }
                if (region.isNotEmpty()) {
                    col.add(region)
                }
                val joinToString = col.joinToString()
                binding.placeWorkText.setText(joinToString)
                binding.industryText.setText(state.currentFilters.industry)
                previousSalary = state.currentFilters.salary
                binding.inputLayoutSalary.editText?.setText(previousSalary)
                Log.d("Salary from SP", "${state.currentFilters.salary}")
                binding.salaryCheckBox.isChecked = state.currentFilters.salaryFlag
            }
        }
    }

    companion object {
        private const val EMPTY = ""
        private const val DEBOUNCE_DELAY = 1000L
    }
}
