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
    @SerializedName("address")
    val address: AddressDTO?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    @SerializedName("area")
    val area: VacancyAreaDTO?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("contacts")
    val contacts: ContactsDTO?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("employer")
    val employer: EmployerDTO?,
    @SerializedName("employment")
    val employment: EmploymentDTO?,
    @SerializedName("experience")
    val experience: ExperienceDTO?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillDTO?>?,
    @SerializedName("manager")
    val manager: ManagerDTO?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("previous_id")
    val previousId: String?,
    @SerializedName("salary")
    val salary: SalaryDTO?,
    @SerializedName("schedule")
    val schedule: ScheduleDTO?
) : NetworkResponse()
