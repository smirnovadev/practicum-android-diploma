package ru.practicum.android.diploma.favorites.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavoritesBinding
import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState
import ru.practicum.android.diploma.favorites.presentation.FavoritesViewModel
import ru.practicum.android.diploma.job.ui.JobFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.adapter.SearchAdapter
import ru.practicum.android.diploma.search.ui.adapter.SearchClickListener

class FavoritesFragment : Fragment(), SearchClickListener {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavoritesViewModel>()
    private var adapter = SearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenState().observe(viewLifecycleOwner) { state ->
            renderState(state)
        }

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderState(state: FavoritesScreenState) {
        when (state) {
            is FavoritesScreenState.Default -> showDefaultState(state.vacancyList)
            is FavoritesScreenState.LoadingError -> showLoadingErrorState()
            is FavoritesScreenState.NoFavoritesAdded -> showNoFavoritesAddedState()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showDefaultState(list: ArrayList<Vacancy>) {
        binding.apply {
            placeholder.isVisible = false
            recyclerView.isVisible = true
            adapter.vacanciesList.clear()
            adapter.vacanciesList.addAll(list)
            adapter.notifyDataSetChanged()
        }
    }

    private fun showNoFavoritesAddedState() {
        binding.apply {
            placeholder.isVisible = true
            placeholder.setText(R.string.empty_list)
            placeholder.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.img_empty_list, 0, 0
            )
            recyclerView.isVisible = false
        }
    }

    private fun showLoadingErrorState() {
        binding.apply {
            placeholder.isVisible = true
            placeholder.setText(R.string.empty_search_results)
            placeholder.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.img_empty_search_results, 0, 0
            )
            recyclerView.isVisible = false
        }
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        findNavController().navigate(
            R.id.action_favoritesFragment_to_jobFragment,
            JobFragment.createArgs(
                vacancy.id
            )
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVacancies()
    }

}
