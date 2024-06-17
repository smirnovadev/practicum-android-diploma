package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.search.domain.model.fields.Address
import ru.practicum.android.diploma.search.domain.model.fields.Contacts
import ru.practicum.android.diploma.search.domain.model.fields.Department
import ru.practicum.android.diploma.search.domain.model.fields.Employer
import ru.practicum.android.diploma.search.domain.model.fields.Employment
import ru.practicum.android.diploma.search.domain.model.fields.Experience
import ru.practicum.android.diploma.search.domain.model.fields.InsiderInterview
import ru.practicum.android.diploma.search.domain.model.fields.KeySkill
import ru.practicum.android.diploma.search.domain.model.fields.Manager
import ru.practicum.android.diploma.search.domain.model.fields.ProfessionalRole
import ru.practicum.android.diploma.search.domain.model.fields.Schedule
import ru.practicum.android.diploma.search.domain.model.fields.Snippet
import ru.practicum.android.diploma.search.domain.model.fields.Type
import ru.practicum.android.diploma.search.domain.model.fields.VacancyArea
import ru.practicum.android.diploma.search.domain.model.fields.WorkingDay
import ru.practicum.android.diploma.search.domain.model.fields.WorkingTimeInterval
import java.io.Serializable

data class Vacancy(
    val address: Address,
    val alternateUrl: String,
    val applyAlternateUrl: String,
    val area: VacancyArea,
    val code: String,
    val contacts: Contacts,
    val description: String,
    val employer: Employer,
    val employment: Employment,
    val experience: Experience,
    val id: String,
    val keySkills: List<KeySkill>,
    val name: String,
    val previousId: String,
    val professionalRoles: List<ProfessionalRole>,
    val responseUrl: String?,
    val salary: String,
    val schedule: Schedule,
) : Serializable
