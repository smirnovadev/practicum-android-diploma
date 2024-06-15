package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
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
    private lateinit var inputCountry: TextInputEditText
    private lateinit var inputRegion: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceToWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputCountry = binding.countryText
        inputRegion = binding.regionText

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }

        country = viewModel.getCountryName()
        inputCountry.setText(country)

        region = viewModel.getRegionName()
        inputRegion.setText(region)

        inputCountry.setOnClickListener {
            if (inputCountry.text?.isEmpty() == true)
                navigateToCountry()
        }
        binding.actionCountry.setOnClickListener {
            if (inputCountry.text?.isEmpty() == true)
                navigateToCountry()
            else {
                clearCountry()
                clearRegion()
            }
        }

        inputRegion.setOnClickListener {
            if (inputRegion.text?.isEmpty() == true)
                navigateToRegion()
        }
        binding.actionRegion.setOnClickListener {
            if (inputRegion.text?.isEmpty() == true)
                navigateToRegion()
            else {
                clearRegion()
            }
        }
    }

    private fun clearCountry() {
        inputCountry.setText(EMPTY)
        viewModel.clearCountryName()
        refreshCountryIcon()
    }

    private fun clearRegion() {
        inputRegion.setText(EMPTY)
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

        inputCountry.setText(viewModel.getCountryName())
        refreshCountryIcon()
        inputRegion.setText(viewModel.getRegionName())
        refreshRegionIcon()
    }

    private fun refreshCountryIcon() {
        binding.actionCountry.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (inputCountry.text?.isEmpty() == true)
                    R.drawable.ic_arrow_forward
                else
                    R.drawable.ic_close,
                activity?.theme
            )
        )
    }

    private fun refreshRegionIcon() {
        binding.actionRegion.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                if (inputRegion.text?.isEmpty() == true)
                    R.drawable.ic_arrow_forward
                else
                    R.drawable.ic_close,
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
