package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.filters.presentation.FiltersViewModel

class FiltersFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FiltersViewModel>()
    private var country: String = EMPTY
    private var region: String = EMPTY
    private var industry: String = EMPTY
    private var salary: String = EMPTY
    private var salaryFlag: Boolean = false

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
        country = viewModel.getCurrentCountry()
        region = viewModel.getCurrentRegion()
        industry = viewModel.getCurrentIndustry()
        salary = viewModel.getCurrentSalary()
        salaryFlag = viewModel.getCurrentSalaryFlag()

        binding.industryText.setText(industry)

        initListeners()
        bottomListeners()
        viewModel.comparisonChanges()

        viewModel.hasChangesLD().observe(viewLifecycleOwner) { hasChanges ->
            when (hasChanges) {
                true -> binding.buttonApply.isVisible = true
                false -> binding.buttonApply.isVisible = false
            }
        }
    }

    private fun initListeners() {
        binding.placeWorkText.setOnClickListener {
            if (binding.placeWorkText.text?.isEmpty() == true) {
                openPlaceToWorkFragment()
            }
            checkButtonsState()
        }
        binding.placeWorkBtn.setOnClickListener {
            if (binding.placeWorkText.text?.isEmpty() == true) {
                openPlaceToWorkFragment()
            } else {
                clearPlaceToWork()
            }
            checkButtonsState()
        }
        binding.industryText.setOnClickListener {
            if (binding.industryText.text?.isEmpty() == true) {
                openIndustryFragment()
            }
            checkButtonsState()
        }
        binding.industryBtn.setOnClickListener {
            if (binding.industryText.text?.isEmpty() == true) {
                openIndustryFragment()
            } else {
                clearIndustry()
            }
            checkButtonsState()
        }
        binding.salaryText.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            viewModel.saveSalary(text?.toString())
            checkButtonsState()
        })
        binding.salaryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveSalaryFlag(isChecked)
            checkButtonsState()
        }
    }

    private fun bottomListeners() {
        binding.buttonApply.setOnClickListener {
            viewModel.applyFilter()
            findNavController().navigateUp()
        }
        binding.reset.setOnClickListener {
            clearPlaceToWork()
            clearIndustry()
            clearSalaryFlag()
            clearSalary()
        }
    }

    private fun checkButtonsState() {
        val visible = !binding.placeWorkText.text.isNullOrEmpty() ||
            !binding.industryText.text.isNullOrEmpty() ||
            !binding.salaryText.text.isNullOrEmpty() ||
            viewModel.getCurrentSalaryFlagN() == true
        binding.buttonApply.isVisible = visible
        binding.reset.isVisible = visible
    }

    private fun clearPlaceToWork() {
        binding.placeWorkText.setText(EMPTY)
        viewModel.clearCountryName()
        viewModel.clearRegionName()
        refreshPlaceToWorkIcon()
    }

    private fun clearIndustry() {
        binding.industryText.setText(EMPTY)
        viewModel.clearIndustryName()
        refreshIndustryIcon()
    }

    private fun clearSalary() {
        binding.salaryText.setText(EMPTY)
        viewModel.clearSalary()
    }

    private fun clearSalaryFlag() {
        binding.salaryCheckBox.isChecked = false
        viewModel.clearSalaryFlag()
    }

    private fun refreshPlaceToWorkIcon() {
        binding.placeWorkBtn.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (binding.placeWorkText.text?.isEmpty() == true) {
                    R.drawable.ic_arrow_forward
                } else {
                    R.drawable.ic_close
                },
                activity?.theme
            )
        )
    }

    private fun refreshIndustryIcon() {
        binding.industryBtn.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (binding.industryText.text?.isEmpty() == true) {
                    R.drawable.ic_arrow_forward
                } else {
                    R.drawable.ic_close
                },
                activity?.theme
            )
        )
    }

    private fun openIndustryFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_industryFragment)
    }

    private fun openPlaceToWorkFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_placeToWorkFragment)
    }

    override fun onResume() {
        super.onResume()
        country = viewModel.getCurrentCountry()
        region = viewModel.getCurrentRegion()

        val col = mutableListOf<String>()
        if (country.isNotEmpty()) {
            col.add(country)
        }
        if (region.isNotEmpty()) {
            col.add(region)
        }
        val joinToString = col.joinToString()
        binding.placeWorkText.setText(joinToString)

        refreshPlaceToWorkIcon()
        binding.industryText.setText(viewModel.getCurrentIndustry())
        refreshIndustryIcon()

        binding.salaryText.setText(viewModel.getCurrentSalary())
        binding.salaryCheckBox.isChecked = viewModel.getCurrentSalaryFlag()

        checkButtonsState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EMPTY = ""
    }
}
