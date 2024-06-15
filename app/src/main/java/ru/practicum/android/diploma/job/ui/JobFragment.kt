package ru.practicum.android.diploma.job.ui

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
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_CITY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_CONTACTS_NAME
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_EMAIL
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_EMPLOYMENT
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_EXPERIENCE
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_ID
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_KEY_SKILLS
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_LOGO_COMPANY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_NAME_COMPANY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_PHONE_NUMBER
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_PROFESSIONAL_ROLES
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_REGION
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_REQUIREMENT
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_RESPONSIBILITY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_SALARY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_SCHEDULE
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_URL
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_VACANCY
import ru.practicum.android.diploma.search.ui.SearchFragment.Companion.EXTRA_VACANCY_NAME

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

        viewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }

        binding.apply {
            icShape.setOnClickListener {
                viewModel.shareLink(requireArguments().getString(EXTRA_URL).toString())
            }

            tvEMail.setOnClickListener {
                viewModel.emailTo(requireArguments().getString(EXTRA_EMAIL).toString())
            }

            tvNumberPhone.setOnClickListener {
                viewModel.callTo(requireArguments().getString(EXTRA_PHONE_NUMBER).toString())
            }

            toolbar.setOnClickListener {
                findNavController().popBackStack()
            }

        }

        viewCompanyLogo()
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

    private fun showContent() {
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

            viewContacts()
            viewCityOrRegion()
            viewDescription()
            viewKeySkills()
            viewEmploymentAndSchedule()

            val bundle = requireArguments()
            val textViews = listOf(
                EXTRA_VACANCY_NAME to tvNameVacancy,
                EXTRA_SALARY to tvSalaryVacancy,
                EXTRA_EXPERIENCE to tvRequiredExperience,
                EXTRA_NAME_COMPANY to tvNameCompany,
            )

            textViews.forEach { (key, textView) ->
                textView.text = bundle.getString(key)
            }
        }
    }

    private fun viewContacts() {
        binding.apply {
            val email = requireArguments().getString(EXTRA_EMAIL).orEmpty()
            val phoneNumber = requireArguments().getString(EXTRA_PHONE_NUMBER).orEmpty()
            val contactName = requireArguments().getString(EXTRA_CONTACTS_NAME).orEmpty()

            if (email.isEmpty()) {
                tvEMail.isVisible = false
                eMail.isVisible = false
            } else {
                eMail.isVisible = true
                tvEMail.isVisible = true
                tvEMail.text = email
            }

            if (phoneNumber.isEmpty()) {
                tvNumberPhone.isVisible = false
                numberPhone.isVisible = false
            } else {
                tvNumberPhone.isVisible = true
                numberPhone.isVisible = true
                tvNumberPhone.text = phoneNumber
            }

            if (contactName.isEmpty()) {
                tvContactPerson.isVisible = false
                contactPerson.isVisible = false
            } else {
                tvContactPerson.isVisible = true
                contactPerson.isVisible = true
                tvContactPerson.text = contactName
            }

            contacts.isVisible = email.isNotEmpty()
                || phoneNumber.isNotEmpty()
                || contactName.isNotEmpty()
        }
    }

    private fun viewDescription() {
        val requirement = requireArguments().getString(EXTRA_REQUIREMENT).orEmpty()
        val responsibilitys = requireArguments().getString(EXTRA_RESPONSIBILITY).orEmpty()

        binding.apply {
            if (requirement.isEmpty()) {
                tvRequirements.isVisible = false
                requirements.isVisible = false
            } else {
                tvRequirements.isVisible = true
                requirements.isVisible = true
                formatHtmlToString(tvRequirements, EXTRA_REQUIREMENT)
            }

            if (responsibilitys.isEmpty()) {
                tvResponsibility.isVisible = false
                responsibility.isVisible = false
            } else {
                tvResponsibility.isVisible = true
                responsibility.isVisible = true
                formatHtmlToString(tvResponsibility, EXTRA_RESPONSIBILITY)
            }
        }
    }

    private fun viewCityOrRegion() {
        binding.apply {
            val city = requireArguments().getString(EXTRA_CITY).orEmpty()
            val region = requireArguments().getString(EXTRA_REGION).orEmpty()

            if (city.isNotEmpty()) {
                tvCity.text = city
            } else {
                tvCity.text = region
            }
        }
    }

    private fun formatHtmlToString(textView: TextView, key: String) {
        val body = requireArguments().getString(key)?.replace(" - ", "\n-")
        textView.text = Html.fromHtml(body, Html.FROM_HTML_MODE_COMPACT)
    }

    private fun viewKeySkills() {
        val keySkill = requireArguments().getString(EXTRA_KEY_SKILLS).orEmpty()
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

    private fun viewCompanyLogo(){
        val cornerSize = resources.getDimensionPixelSize(R.dimen._12dp)
        Glide.with(this)
            .load(requireArguments().getString(EXTRA_LOGO_COMPANY))
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_card_list)
            .transform(RoundedCorners(cornerSize))
            .into(binding.icCompany)
    }

    private fun viewEmploymentAndSchedule() {
        val employment = requireArguments().getString(EXTRA_EMPLOYMENT)
        val schedul = requireArguments().getString(EXTRA_SCHEDULE)

        binding.tvWorkSchedule.text = "$employment, $schedul"
    }

    companion object {
        fun createArgs(
            vacancyId: String,
            vacancyName: String?,
            vacancySalary: String,
            vacancyExperience: String?,
            vacancyCity: String,
            vacancyCompany: String,
            keySkills: String?,
            contactName: String?,
            email: String?,
            vacancyUrl: String,
            vacancyPhone: String?,
            vacancyLogo: String?,
            professionalRoles: String?,
            vacancyRegion: String?,
            requirement: String?,
            responsibility: String?,
            employment: String?,
            schedule: String?,
            vacancy: Vacancy
        ): Bundle = bundleOf(
            EXTRA_ID to vacancyId,
            EXTRA_VACANCY_NAME to vacancyName,
            EXTRA_SALARY to vacancySalary,
            EXTRA_VACANCY to vacancy,
            EXTRA_EXPERIENCE to vacancyExperience,
            EXTRA_CITY to vacancyCity,
            EXTRA_NAME_COMPANY to vacancyCompany,
            EXTRA_KEY_SKILLS to keySkills,
            EXTRA_CONTACTS_NAME to contactName,
            EXTRA_EMAIL to email,
            EXTRA_URL to vacancyUrl,
            EXTRA_PHONE_NUMBER to vacancyPhone,
            EXTRA_LOGO_COMPANY to vacancyLogo,
            EXTRA_PROFESSIONAL_ROLES to professionalRoles,
            EXTRA_REGION to vacancyRegion,
            EXTRA_REQUIREMENT to requirement,
            EXTRA_RESPONSIBILITY to responsibility,
            EXTRA_EMPLOYMENT to employment,
            EXTRA_SCHEDULE to schedule,
        )
    }
}
