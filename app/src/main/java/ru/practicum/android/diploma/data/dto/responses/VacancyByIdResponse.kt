package ru.practicum.android.diploma.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.fields.AddressDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ContactsDTO
import ru.practicum.android.diploma.data.dto.responses.fields.EmployerDTO
import ru.practicum.android.diploma.data.dto.responses.fields.EmploymentDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ExperienceDTO
import ru.practicum.android.diploma.data.dto.responses.fields.KeySkillDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ManagerDTO
import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ScheduleDTO
import ru.practicum.android.diploma.data.dto.responses.fields.VacancyAreaDTO

/**
 * https://api.hh.ru/openapi/redoc#tag/Vakansii/operation/get-vacancy
 */

data class VacancyByIdResponse(
    val address: AddressDTO?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    val area: VacancyAreaDTO?,
    val contacts: ContactsDTO?,
    val description: String?,
    val employer: EmployerDTO?,
    val employment: EmploymentDTO?,
    val experience: ExperienceDTO?,
    val id: String?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillDTO?>?,
    val manager: ManagerDTO?,
    val name: String?,
    val salary: SalaryDTO?,
    val schedule: ScheduleDTO?
) : NetworkResponse()
