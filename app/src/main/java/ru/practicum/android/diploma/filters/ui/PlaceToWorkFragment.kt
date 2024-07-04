package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentPlaceToWorkBinding
import ru.practicum.android.diploma.filters.presentation.PlaceToWorkViewModel

class PlaceToWorkFragment : Fragment() {
    private var _binding: FragmentPlaceToWorkBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PlaceToWorkViewModel>()

    private var country: String = EMPTY
    private var region: String = EMPTY

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceToWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        country = viewModel.getCountryName()
        binding.countryText.setText(country)

        region = viewModel.getRegionName()
        binding.regionText.setText(region)

        initListeners()
        checkButtonsState()
    }

    private fun initListeners() {
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
            clearCountry()
            clearRegion()
        }
        binding.buttonApply.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.countryText.setOnClickListener {
            if (binding.countryText.text?.isEmpty() == true) {
                navigateToCountry()
            }
        }
        binding.actionCountry.setOnClickListener {
            if (binding.countryText.text?.isEmpty() == true) {
                navigateToCountry()
            } else {
                clearCountry()
                clearRegion()
            }
        }
        binding.regionText.setOnClickListener {
            if (binding.regionText.text?.isEmpty() == true) {
                navigateToRegion()
            }
        }
        binding.actionRegion.setOnClickListener {
            if (binding.regionText.text?.isEmpty() == true) {
                navigateToRegion()
            } else {
                clearRegion()
            }
        }
    }

    private fun checkButtonsState() {
        if (!binding.countryText.text.isNullOrEmpty()) {
            binding.buttonApply.isVisible = true
        }
    }

    private fun clearCountry() {
        binding.countryText.setText(EMPTY)
        viewModel.clearCountryName()
        refreshCountryIcon()
    }

    private fun clearRegion() {
        binding.regionText.setText(EMPTY)
        viewModel.clearRegionName()
        refreshRegionIcon()
    }

    private fun navigateToRegion() {
        findNavController().navigate(R.id.action_placeToWorkFragment_to_regionFragment)
    }

    private fun navigateToCountry() {
        findNavController().navigate(R.id.action_placeToWorkFragment_to_countryFragment)
    }

    override fun onResume() {
        super.onResume()

        binding.countryText.setText(viewModel.getCountryName())
        refreshCountryIcon()
        binding.regionText.setText(viewModel.getRegionName())
        refreshRegionIcon()
        checkButtonsState()
    }

    private fun refreshCountryIcon() {
        binding.actionCountry.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (binding.countryText.text?.isEmpty() == true) {
                    R.drawable.ic_arrow_forward
                } else {
                    R.drawable.ic_close
                },
                activity?.theme
            )
        )
    }

    private fun refreshRegionIcon() {
        binding.actionRegion.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (binding.regionText.text?.isEmpty() == true) {
                    R.drawable.ic_arrow_forward
                } else {
                    R.drawable.ic_close
                },
                activity?.theme
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EMPTY = ""
    }
}
