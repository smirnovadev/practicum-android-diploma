package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.placeWorkBtn.setOnClickListener { openPlaceToWorkFragment() }
        binding.placeWorkText.setOnClickListener { openPlaceToWorkFragment() }
        binding.industryText.setOnClickListener { openIndustryFragment() }
        binding.industryBtn.setOnClickListener { openIndustryFragment() }

    }

    private fun openIndustryFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_industryFragment)
    }

    private fun openPlaceToWorkFragment() {
        findNavController().navigate(R.id.action_filtersFragment_to_placeToWorkFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}
