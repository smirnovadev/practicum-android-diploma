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
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getScreenState().observe(viewLifecycleOwner) {
            render(it)
        }

        val vacancyId = requireArguments().getString(EXTRA_ID)
        if (vacancyId != null) {
            viewModel.searchVacancyById(vacancyId)
            viewModel.loadFavoriteState(vacancyId)
        }

        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        val addFavoriteButton = binding.icFavorites
        viewModel.isFavoriteVacancyLiveData().observe(requireActivity()) { isFavorite ->
            addFavoriteButton.setBackgroundResource(
                if (isFavorite) {
                    R.drawable.ic_favorites_on
                } else {
                    R.drawable.ic_favorites_off
                }
            )
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
            icFavorites.setOnClickListener {
                viewModel.onFavoriteClicked(vacancy)
            }
        }
    }

    private fun viewContacts(vacancy: Vacancy) {
        binding.apply {
            setupContactField(vacancy.contacts.email, tvEMail, eMail) { email ->
                tvEMail.text = email
                tvEMail.setOnClickListener {
                    viewModel.emailTo(email)
                }
            }

            val phoneNumber = vacancy.contacts.phones.joinToString(separator = " + ") {
                "+${it?.country} (${it?.city ?: ""})  ${it?.number ?: ""}"
            }
            setupContactField(phoneNumber, tvNumberPhone, numberPhone) { phone ->
                tvNumberPhone.text = phone
                tvNumberPhone.setOnClickListener {
                    viewModel.callTo(phone)
                }
            }

            setupContactField(vacancy.contacts.name, tvContactPerson, contactPerson) { name ->
                tvContactPerson.text = name
            }

            val comments = vacancy.contacts.phones.joinToString(separator = " ") {
                it?.comment ?: ""
            }
            setupContactField(comments, tvComment, comment) { commentText ->
                tvComment.text = commentText
            }

            contacts.isVisible = listOf(
                vacancy.contacts.email,
                phoneNumber,
                vacancy.contacts.name,
                comments
            ).any { it?.isNotEmpty() == true }
        }
    }

    private fun setupContactField(
        value: String?,
        textView: TextView,
        container: View,
        setupAction: (String) -> Unit
    ) {
        if (value.isNullOrEmpty()) {
            textView.isVisible = false
            container.isVisible = false
        } else {
            textView.isVisible = true
            container.isVisible = true
            setupAction(value)
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

        binding.tvWorkSchedule.text = getString(R.string.tv_work_schedule_template, employment, schedul)
    }

    private fun formatHtml(html: String): String {
        return html.replace("<b>", "\n<b>")
            .replace("</b>", "</b>\n")
            .replace("<li>", "<li> ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun createArgs(
            vacancyId: String
        ): Bundle = bundleOf(
            EXTRA_ID to vacancyId
        )
    }
}
