package ru.practicum.android.diploma.filters.ui.industry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.filters.presentation.IndustryViewModel
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryFragment : Fragment() {
    private var _binding: FragmentIndustryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<IndustryViewModel>()

    private var rvAdapter: IndustryAdapter? = null
    private lateinit var recycler: RecyclerView

    private val industries = mutableListOf<Industry>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        recycler = binding.recyclerView
        rvAdapter = IndustryAdapter(industries) {
            viewModel.save(it)
            findNavController().navigateUp()
        }
        recycler.adapter = rvAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is IndustryState.Content -> showContent(state.industryList)
                IndustryState.Empty -> showEmpty()
                is IndustryState.Error -> showError(state.code)
                IndustryState.Loading -> showLoading()
            }
        }
    }

    private fun showContent(industryList: List<Industry>) {
        industries.clear()
        industries.addAll(industryList)
        binding.recyclerView.visibility = View.VISIBLE
        rvAdapter!!.notifyItemRangeChanged(0, industryList.size)
    }

    private fun showEmpty() {
//        TODO()
    }

    private fun showError(code: Int) {
//        TODO()
    }

    private fun showLoading() {
//        TODO()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rvAdapter = null
        recycler.adapter = null
    }
}
