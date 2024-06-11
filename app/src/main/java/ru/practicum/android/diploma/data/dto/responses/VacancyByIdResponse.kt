package ru.practicum.android.diploma.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.fields.Address
import ru.practicum.android.diploma.data.dto.responses.fields.Contacts
import ru.practicum.android.diploma.data.dto.responses.fields.Department
import ru.practicum.android.diploma.data.dto.responses.fields.Employer
import ru.practicum.android.diploma.data.dto.responses.fields.Experience
import ru.practicum.android.diploma.data.dto.responses.fields.InsiderInterview
import ru.practicum.android.diploma.data.dto.responses.fields.KeySkill
import ru.practicum.android.diploma.data.dto.responses.fields.Manager
import ru.practicum.android.diploma.data.dto.responses.fields.ProfessionalRole
import ru.practicum.android.diploma.data.dto.responses.fields.Salary
import ru.practicum.android.diploma.data.dto.responses.fields.Schedule
import ru.practicum.android.diploma.data.dto.responses.fields.Type
import ru.practicum.android.diploma.data.dto.responses.fields.VacancyArea
import ru.practicum.android.diploma.data.dto.responses.fields.WorkingDay
import ru.practicum.android.diploma.data.dto.responses.fields.WorkingTimeInterval

/**
 * https://api.hh.ru/openapi/redoc#tag/Vakansii/operation/get-vacancy
 */

data class VacancyByIdResponse(
    @SerializedName("address")
    val address: Address,
    @SerializedName("alternate_url")
    val alternateUrl: String,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("archived")
    val archived: Boolean,
    @SerializedName("area")
    val area: VacancyArea,
    @SerializedName("code")
    val code: String,
    @SerializedName("contacts")
    val contacts: Contacts,
    @SerializedName("department")
    val department: Department,
    @SerializedName("description")
    val description: String,
    @SerializedName("employer")
    val employer: Employer,
    @SerializedName("experience")
    val experience: Experience,
    @SerializedName("has_test")
    val hasTest: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterview,
    @SerializedName("key_skills")
    val keySkills: List<KeySkill>,
    @SerializedName("manager")
    val manager: Manager,
    @SerializedName("name")
    val name: String,
    @SerializedName("premium")
    val premium: Boolean,
    @SerializedName("previous_id")
    val previousId: String,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean,
    @SerializedName("response_notifications")
    val responseNotifications: Boolean,
    @SerializedName("response_url")
    val responseUrl: String?,
    @SerializedName("salary")
    val salary: Salary,
    @SerializedName("schedule")
    val schedule: Schedule,
    @SerializedName("type")
    val type: Type,
    @SerializedName("working_days")
    val workingDays: List<WorkingDay>,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<WorkingTimeInterval>,
) : NetworkResponse()
