package ru.practicum.android.diploma.data.dto.vacancies

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.vacancies.fields.Address

data class VacancyByIdResponse(
    @SerializedName("accept_handicapped")
    val acceptHandicapped: Boolean,
    @SerializedName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean,
    @SerializedName("accept_kids")
    val acceptKids: Boolean,
    @SerializedName("accept_temporary")
    val acceptTemporary: Boolean,
    @SerializedName("address")
    val address: Address,
    @SerializedName("allow_messages")
    val allowMessages: Boolean,
    @SerializedName("alternate_url")
    val alternateUrl: String,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("archived")
    val archived: Boolean,
    @SerializedName("area")
    val area: ru.practicum.android.diploma.data.dto.vacancies.fields.VacancyArea,
    @SerializedName("billing_type")
    val brandedDescription: String,
    @SerializedName("can_upgrade_billing_type")
    val canUpgradeBillingType: Boolean,
    @SerializedName("code")
    val code: String,
    @SerializedName("contacts")
    val contacts: ru.practicum.android.diploma.data.dto.vacancies.fields.Contacts,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("department")
    val department: ru.practicum.android.diploma.data.dto.vacancies.fields.Department,
    @SerializedName("description")
    val description: String,
    @SerializedName("employer")
    val employer: ru.practicum.android.diploma.data.dto.vacancies.fields.Employer,
    @SerializedName("experience")
    val experience: ru.practicum.android.diploma.data.dto.vacancies.fields.Experience,
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("has_test")
    val hasTest: Boolean,
    @SerializedName("hidden")
    val hidden: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("initial_created_at")
    val initialCreatedAt: String,
    @SerializedName("insider_interview")
    val insiderInterview: ru.practicum.android.diploma.data.dto.vacancies.fields.InsiderInterview,
    @SerializedName("key_skills")
    val keySkills: List<ru.practicum.android.diploma.data.dto.vacancies.fields.KeySkill>,
    @SerializedName("manager")
    val manager: ru.practicum.android.diploma.data.dto.vacancies.fields.Manager,
    @SerializedName("name")
    val name: String,
    @SerializedName("premium")
    val premium: Boolean,
    @SerializedName("previous_id")
    val previousId: String,
    @SerializedName("professional_roles")
    val professionalRoles: List<ru.practicum.android.diploma.data.dto.vacancies.fields.ProfessionalRole>,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean,
    @SerializedName("response_notifications")
    val responseNotifications: Boolean,
    @SerializedName("response_url")
    val responseUrl: String?,
    @SerializedName("salary")
    val salary: ru.practicum.android.diploma.data.dto.vacancies.fields.Salary,
    @SerializedName("schedule")
    val schedule: ru.practicum.android.diploma.data.dto.vacancies.fields.Schedule,
    @SerializedName("type")
    val type: ru.practicum.android.diploma.data.dto.vacancies.fields.Type,
    @SerializedName("working_days")
    val workingDays: List<ru.practicum.android.diploma.data.dto.vacancies.fields.WorkingDay>,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<ru.practicum.android.diploma.data.dto.vacancies.fields.WorkingTimeInterval>,
)
