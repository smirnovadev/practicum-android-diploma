package ru.practicum.android.diploma.data.dto.responses.vacancies

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.fields.AddressDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ContactsDTO
import ru.practicum.android.diploma.data.dto.responses.fields.DepartmentDTO
import ru.practicum.android.diploma.data.dto.responses.fields.EmployerDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ExperienceDTO
import ru.practicum.android.diploma.data.dto.responses.fields.InsiderInterviewDTO
import ru.practicum.android.diploma.data.dto.responses.fields.KeySkillDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ManagerDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ProfessionalRoleDTO
import ru.practicum.android.diploma.data.dto.responses.fields.SalaryDTO
import ru.practicum.android.diploma.data.dto.responses.fields.ScheduleDTO
import ru.practicum.android.diploma.data.dto.responses.fields.TypeDTO
import ru.practicum.android.diploma.data.dto.responses.fields.VacancyAreaDTO
import ru.practicum.android.diploma.data.dto.responses.fields.WorkingDayDTO
import ru.practicum.android.diploma.data.dto.responses.fields.WorkingTimeIntervalDTO

/**
 * https://api.hh.ru/openapi/redoc#tag/Vakansii/operation/get-vacancy
 */
data class VacancyDTO(
    @SerializedName("address")
    val address: AddressDTO?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    @SerializedName("approved")
    val approved: Boolean?,
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("area")
    val area: VacancyAreaDTO?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("contacts")
    val contacts: ContactsDTO?,
    @SerializedName("department")
    val department: DepartmentDTO?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("employer")
    val employer: EmployerDTO?,
    @SerializedName("experience")
    val experience: ExperienceDTO?,
    @SerializedName("has_test")
    val hasTest: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterviewDTO?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillDTO?>?,
    @SerializedName("manager")
    val manager: ManagerDTO?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("premium")
    val premium: Boolean?,
    @SerializedName("previous_id")
    val previousId: String?,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRoleDTO?>?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean?,
    @SerializedName("response_notifications")
    val responseNotifications: Boolean?,
    @SerializedName("response_url")
    val responseUrl: String?,
    @SerializedName("salary")
    val salary: SalaryDTO?,
    @SerializedName("schedule")
    val schedule: ScheduleDTO?,
    @SerializedName("type")
    val type: TypeDTO?,
    @SerializedName("working_days")
    val workingDays: List<WorkingDayDTO?>?,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<WorkingTimeIntervalDTO?>?
) : NetworkResponse()
