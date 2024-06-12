package ru.practicum.android.diploma.job.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.job.domain.JobScreenState
import ru.practicum.android.diploma.job.ui.viewModel.JobViewModel


class JobFragment : Fragment() {

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<JobViewModel>()

    val url = "" // врменно, заменить на получаемую ссылку с сервера
    val email = "i.lozgkina@yandex.ru" // также
    val phoneNumber = "" // и тут
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            icShape.setOnClickListener {
                viewModel.shareLink(url)
            }

            tvEMail.setOnClickListener {
                viewModel.emailTo(email)
            }

            tvNumberPhone.setOnClickListener {
                viewModel.callTo(phoneNumber)
            }

            toolbar.setOnClickListener {
                findNavController().popBackStack()
            }

//            icFavorites.setOnClickListener {
//                lifecycleScope.launch {
//                    if (vacancy != null) {
//                        viewModel.addFavoriteVacancy(vacancy)
//                    }
//                }
//            }

        }

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }


//        viewModel.likeState.observe(viewLifecycleOwner) { isLiked ->
//            binding.apply {
//                if (isLiked) {
//                    icFavorites.setImageResource(R.drawable.ic_favorites_on)
//                    if (vacancy != null) vacancy.isFavorite = isLiked
//                } else {
//                    icFavorites.setImageResource(R.drawable.ic_favorites_off)
//                    if (vacancy != null) vacancy.isFavorite = isLiked
//                }
//            }
//        }
    }

    private fun render(state: JobScreenState) {
        when (state) {
            is JobScreenState.Loading -> showLoading()
            is JobScreenState.Error -> showError()
            is JobScreenState.Content -> showContent()
        }
    }

    private fun showLoading() {
        binding.apply {
            progressBar.isVisible = true
            icFavorites.isVisible = false // нужно будет уточнить у наставника
            icShape.isVisible = false // и про это тоже
            tvNameVacancy.isVisible = false
            tvSalaryVacancy.isVisible = false
            constraintLayoutCard.isVisible = false
            scrollView.isVisible = false
            imgErrorServer.isVisible = false
            errorServer.isVisible = false
        }
    }

    private fun showError() {
        binding.apply {
            progressBar.isVisible = false
            icFavorites.isVisible = false // нужно будет уточнить у наставника
            icShape.isVisible = false // и про это тоже
            tvNameVacancy.isVisible = false
            tvSalaryVacancy.isVisible = false
            constraintLayoutCard.isVisible = false
            scrollView.isVisible = false
            imgErrorServer.isVisible = true
            errorServer.isVisible = true
        }
    }

    private fun showContent() {
        binding.apply {
            progressBar.isVisible = false
            icFavorites.isVisible = true // нужно будет уточнить у наставника
            icShape.isVisible = true // и про это тоже
            tvNameVacancy.isVisible = true
            tvSalaryVacancy.isVisible = true
            constraintLayoutCard.isVisible = true
            scrollView.isVisible = true
            imgErrorServer.isVisible = false
            errorServer.isVisible = false
        }
    }

}
