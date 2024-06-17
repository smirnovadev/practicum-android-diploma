package ru.practicum.android.diploma.job.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.job.domain.JobScreenState
import ru.practicum.android.diploma.job.ui.viewmodel.JobViewModel
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_ID

class JobFragment : Fragment() {

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<JobViewModel>()


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

        viewModel.getScreenState().observe(viewLifecycleOwner) {
            render(it)
        }

        val vacancyId = requireArguments().getString(EXTRA_ID)
        if (vacancyId != null) viewModel.searchVacancyById(vacancyId)

        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun render(state: JobScreenState) {
        when (state) {
            is JobScreenState.Loading -> showLoading()
            is JobScreenState.Error -> showError()
            is JobScreenState.Content -> showContent(state.vacancy)
        }
    }

    private fun showLoading() {
        binding.apply {
            progressBar.isVisible = true
            icFavorites.isVisible = false
            icShape.isVisible = false
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
            icFavorites.isVisible = false
            icShape.isVisible = false
            tvNameVacancy.isVisible = false
            tvSalaryVacancy.isVisible = false
            constraintLayoutCard.isVisible = false
            scrollView.isVisible = false
            imgErrorServer.isVisible = true
            errorServer.isVisible = true
        }
    }

    private fun showContent(vacancy: Vacancy) {
        binding.apply {
            progressBar.isVisible = false
            icFavorites.isVisible = true
            icShape.isVisible = true
            tvNameVacancy.isVisible = true
            tvSalaryVacancy.isVisible = true
            constraintLayoutCard.isVisible = true
            scrollView.isVisible = true
            imgErrorServer.isVisible = false
            errorServer.isVisible = false

            tvNameVacancy.text = vacancy.name
            tvSalaryVacancy.text = vacancy.salary
            tvRequiredExperience.text = vacancy.experience.name
            tvNameCompany.text = vacancy.employer.name
            tvDescription.text = Html.fromHtml(
                formatHtml(vacancy.description),
                Html.FROM_HTML_MODE_COMPACT
            )
            viewContacts(vacancy)
            viewCityOrRegion(vacancy)
            viewKeySkills(vacancy)
            viewEmploymentAndSchedule(vacancy)
            viewCompanyLogo(vacancy)

            icShape.setOnClickListener {
                viewModel.shareLink(vacancy.alternateUrl)
            }
        }
    }

    private fun viewContacts(vacancy: Vacancy) {
        binding.apply {
            val email = vacancy.contacts.email ?: ""
            val phoneNumber = vacancy.contacts.phones.joinToString(separator = " + ") {
                "+${it?.country} (${it?.city ?: ""})  ${it?.number ?: ""}"
            }
            val contactName = vacancy.contacts.name ?: ""
            val comments = vacancy.contacts.phones.joinToString(separator = " ") {
                it?.comment ?: ""
            }

            if (email.isEmpty()) {
                tvEMail.isVisible = false
                eMail.isVisible = false
            } else {
                eMail.isVisible = true
                tvEMail.isVisible = true
                tvEMail.text = email
                tvEMail.setOnClickListener {
                    viewModel.emailTo(email)
                }
            }

            if (phoneNumber.isEmpty()) {
                tvNumberPhone.isVisible = false
                numberPhone.isVisible = false
            } else {
                tvNumberPhone.isVisible = true
                numberPhone.isVisible = true
                tvNumberPhone.text = phoneNumber
                tvNumberPhone.setOnClickListener {
                    viewModel.callTo(phoneNumber)
                }
            }

            if (contactName.isEmpty()) {
                tvContactPerson.isVisible = false
                contactPerson.isVisible = false
            } else {
                tvContactPerson.isVisible = true
                contactPerson.isVisible = true
                tvContactPerson.text = contactName
            }

            if (comments.isEmpty()) {
                tvComment.isVisible = false
                comment.isVisible = false
            } else {
                tvComment.isVisible = true
                comment.isVisible = true
                tvComment.text = comments
            }

            contacts.isVisible = email.isNotEmpty()
                || phoneNumber.isNotEmpty()
                || contactName.isNotEmpty()
                || comments.isNotEmpty()
        }
    }

    private fun viewCityOrRegion(vacancy: Vacancy) {
        binding.apply {
            val city = vacancy.address.city
            val region = vacancy.area.name

            if (city.isNotEmpty()) {
                tvCity.text = city
            } else {
                tvCity.text = region
            }
        }
    }

    private fun viewKeySkills(vacancy: Vacancy) {
        val keySkill = vacancy.keySkills.joinToString("\n - ") { it.name ?: "" }
        binding.apply {
            if (keySkill.isEmpty()) {
                tvKeySkills.isVisible = false
                keySkills.isVisible = false
            } else {
                tvKeySkills.isVisible = true
                keySkills.isVisible = true
                tvKeySkills.text = keySkill
            }
        }
    }

    private fun viewCompanyLogo(vacancy: Vacancy) {
        val cornerSize = resources.getDimensionPixelSize(R.dimen._12dp)
        Glide.with(this)
            .load(vacancy.employer.logoUrls?.original)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_card_list)
            .transform(RoundedCorners(cornerSize))
            .into(binding.icCompany)
    }

    @SuppressLint("SetTextI18n")
    private fun viewEmploymentAndSchedule(vacancy: Vacancy) {
        val employment = vacancy.employment.name
        val schedul = vacancy.schedule.name

        binding.tvWorkSchedule.text = "$employment, $schedul"
    }

    private fun formatHtml(html: String): String {
        return html.replace("<b>", "\n<b>")
            .replace("</b>", "</b>\n")
            .replace("<li>", "<li> ")
    }

    companion object {
        fun createArgs(
            vacancyId: String
        ): Bundle = bundleOf(
            EXTRA_ID to vacancyId
        )
    }
}
