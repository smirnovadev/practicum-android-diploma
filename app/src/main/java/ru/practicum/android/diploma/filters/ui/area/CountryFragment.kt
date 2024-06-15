package ru.practicum.android.diploma.filters.ui.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentSelectCountryBinding
import ru.practicum.android.diploma.filters.presentation.CountryViewModel
import ru.practicum.android.diploma.search.domain.model.fields.Area

class CountryFragment : Fragment() {
    private var _binding: FragmentSelectCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CountryViewModel>()

    private var rvAdapter: AreaAdapter? = null
    private lateinit var recycler: RecyclerView

    private val countries = mutableListOf<Area>()

    private lateinit var groupEmpty: Group

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupEmpty = binding.groupEmpty
        groupEmpty.visibility = View.GONE

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        recycler = binding.recyclerView
        rvAdapter = AreaAdapter(countries) {
            viewModel.save(it)
            findNavController().navigateUp()
        }
        recycler.adapter = rvAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is AreasState.Content -> showContent(state.areasList)
                AreasState.Empty -> showEmpty()
                is AreasState.Error -> showError(state.code)
                AreasState.Loading -> showLoading()
            }
        }
    }

    private fun showContent(countriesList: List<Area>) {
        countries.clear()
        countries.addAll(countriesList)
        binding.recyclerView.isVisible = true
        rvAdapter!!.notifyItemRangeChanged(0, countriesList.size)
        binding.apply {
            groupEmpty.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showEmpty() {
        binding.apply {
            groupEmpty.isVisible = true
            progressBar.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showError(code: Int) {
        binding.apply {
            groupEmpty.isVisible = true
            progressBar.isVisible = false
            recyclerView.isVisible = false
        }
    }

    private fun showLoading() {
        binding.apply {
            groupEmpty.isVisible = false
            progressBar.isVisible = true
            recyclerView.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rvAdapter = null
        recycler.adapter = null
    }
}
