package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
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
    private lateinit var inputPlaceToWork: TextInputEditText
    private lateinit var inputIndustry: TextInputEditText
    private lateinit var inputSalary: TextInputEditText

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputPlaceToWork = binding.placeWorkText
        inputIndustry = binding.industryText
        inputSalary = binding.salaryText

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        bottomNavView = requireActivity().findViewById(R.id.bottomNavigationView)
        bottomNavView.isVisible = false

        country = viewModel.getCountryName()
        region = viewModel.getRegionName()
        industry = viewModel.getIndustryName()

        inputPlaceToWork.setText(getString(R.string.place_to_work_text, country, region))
        inputIndustry.setText(industry)

        inputPlaceToWork.setOnClickListener {
            if (inputPlaceToWork.text?.isEmpty() == true)
                openPlaceToWorkFragment()
        }
        binding.placeWorkBtn.setOnClickListener {
            if (inputPlaceToWork.text?.isEmpty() == true)
                openPlaceToWorkFragment()
            else {
                clearPlaceToWork()
            }
        }


        inputIndustry.setOnClickListener {
            if (inputIndustry.text?.isEmpty() == true)
                openIndustryFragment()
        }
        binding.industryBtn.setOnClickListener {
            if (inputIndustry.text?.isEmpty() == true)
                openIndustryFragment()
            else {
                clearIndustry()
            }
        }
        binding.buttonApply.setOnClickListener {
            findNavController().navigateUp()
        }


        binding.reset.setOnClickListener {
            clearPlaceToWork()
            clearIndustry()
        }
    }

    private fun clearPlaceToWork() {
        inputPlaceToWork.setText(EMPTY)
        viewModel.clearCountryName()
        viewModel.clearRegionName()
        refreshPlaceToWorkIcon()
    }

    private fun clearIndustry() {
        inputIndustry.setText(EMPTY)
        viewModel.clearIndustryName()
        refreshIndustryIcon()
    }

    private fun clearSalary() {
        inputSalary.setText(EMPTY)
//        viewModel.clearSalary()
    }

    private fun refreshPlaceToWorkIcon() {
        binding.placeWorkBtn.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (inputPlaceToWork.text?.isEmpty() == true)
                    R.drawable.ic_arrow_forward
                else
                    R.drawable.ic_close,
                activity?.theme
            )
        )
    }

    private fun refreshIndustryIcon() {
        binding.industryBtn.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (inputIndustry.text?.isEmpty() == true)
                    R.drawable.ic_arrow_forward
                else
                    R.drawable.ic_close,
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
        country = viewModel.getCountryName()
        region = viewModel.getRegionName()
        if (country.isEmpty() && region.isEmpty()) {
            inputPlaceToWork.setText(EMPTY)
        } else {
            inputPlaceToWork.setText(
                getString(
                    R.string.place_to_work_text,
                    country,
                    region
                )
            )
        }
        refreshPlaceToWorkIcon()
        inputIndustry.setText(viewModel.getIndustryName())
        refreshIndustryIcon()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EMPTY = ""
    }
}
