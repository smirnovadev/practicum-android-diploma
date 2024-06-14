package ru.practicum.android.diploma.filters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceToWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.countryText.setOnClickListener{
            findNavController().navigate(R.id.action_placeToWorkFragment_to_countryFragment)
        }
        binding.regionText.setOnClickListener {
            findNavController().navigate(R.id.action_placeToWorkFragment_to_regionFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
