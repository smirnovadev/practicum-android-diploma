package ru.practicum.android.diploma.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavoritesBinding
import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState
import ru.practicum.android.diploma.favorites.presentation.FavoritesViewModel

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenState().observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderState(state: FavoritesScreenState) {
        when (state) {
            is FavoritesScreenState.Default -> showDefaultState()
            is FavoritesScreenState.LoadingError -> showLoadingErrorState()
            is FavoritesScreenState.NoFavoritesAdded -> showNoFavoritesAddedState()
        }
    }

    private fun showDefaultState() {
        binding.apply {
            placeholder.isVisible = false
            recyclerView.isVisible = true
        }
    }

    private fun showNoFavoritesAddedState() {
        binding.apply {
            placeholder.setText(R.string.empty_list)
            placeholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_empty_list,
                0,
                0
            )
            recyclerView.isVisible = false
        }
    }

    private fun showLoadingErrorState() {
        binding.apply {
            placeholder.setText(R.string.empty_search_results)
            placeholder.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.img_empty_search_results,
                0,
                0
            )
            recyclerView.isVisible = false
        }
    }
}
