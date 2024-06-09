package ru.practicum.android.diploma.data.dto.vacancies.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.vacancies.fields.Address

data class SearchVacanciesUnit(
    // Ссылка на сайт HH с вакансией
    @SerializedName("alternate_url")
    val alternateUrl: String,

    // Адресс
    @SerializedName("address")
    val address: Address,

    // Регион
    @SerializedName("area")
    val area: ru.practicum.android.diploma.data.dto.vacancies.fields.VacancyArea,

    // Контактная информация
    @SerializedName("contacts")
    val contacts: ru.practicum.android.diploma.data.dto.vacancies.fields.Contacts,

    // Идентификатор Вакансии
    @SerializedName("id")
    val id: String,

    // Дополнительные поля
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String,

    @SerializedName("brand_snippet")
    val brandSnippet: ru.practicum.android.diploma.data.dto.vacancies.fields.BrandSnippet,
    @SerializedName("branding")
    val branding: ru.practicum.android.diploma.data.dto.vacancies.fields.Branding,
    @SerializedName("counters")
    val counters: ru.practicum.android.diploma.data.dto.vacancies.fields.Counters,
    @SerializedName("department")
    val department: ru.practicum.android.diploma.data.dto.vacancies.fields.Department,
    @SerializedName("employer")
    val employer: ru.practicum.android.diploma.data.dto.vacancies.fields.Employer,
    @SerializedName("has_test")
    val hasTest: Boolean,
    @SerializedName("insider_interview")
    val insiderInterview: ru.practicum.android.diploma.data.dto.vacancies.fields.InsiderInterview,

    // Название вакансии
    @SerializedName("name")
    val name: String,

    @SerializedName("personal_data_resale")
    val personalDataResale: Boolean,
    @SerializedName("professional_roles")
    val professionalRoles: List<ru.practicum.android.diploma.data.dto.vacancies.fields.ProfessionalRole>,
    @SerializedName("published_at")
    val publishedAt: String,

    // Связи
    @SerializedName("relations")
    val relations: List<Any>,

    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean,
    @SerializedName("response_url")
    val responseUrl: Any,

    // Зарплата
    @SerializedName("salary")
    val salary: ru.practicum.android.diploma.data.dto.vacancies.fields.Salary,

    // График работы
    @SerializedName("schedule")
    val schedule: ru.practicum.android.diploma.data.dto.vacancies.fields.Schedule,

    @SerializedName("show_logo_in_search")
    val showLogoInSearch: Boolean,

    // Описание вакансии
    @SerializedName("snippet")
    val snippet: ru.practicum.android.diploma.data.dto.vacancies.fields.Snippet,

    @SerializedName("sort_point_distance")
    val sortPointDistance: Double,

    // Тип вакансии
    @SerializedName("type")
    val type: ru.practicum.android.diploma.data.dto.vacancies.fields.Type,

    // Ссылка на вакансию
    @SerializedName("url")
    val url: String
)
