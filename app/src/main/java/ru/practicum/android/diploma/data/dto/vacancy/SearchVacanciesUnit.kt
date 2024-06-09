package ru.practicum.android.diploma.data.dto.vacancy

import com.google.gson.annotations.SerializedName

data class SearchVacanciesUnit(
    // Ссылка на сайт HH с вакансией
    @SerializedName("alternate_url")
    val alternateUrl: String,

    // Адресс
    @SerializedName("address")
    val address: Address,

    // Регион
    @SerializedName("area")
    val area: VacancyArea,

    // Контактная информация
    @SerializedName("contacts")
    val contacts: Contacts,

    // Идентификатор Вакансии
    @SerializedName("id")
    val id: String,

    // Дополнительные поля
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String,

    @SerializedName("brand_snippet")
    val brandSnippet: BrandSnippet,
    @SerializedName("branding")
    val branding: Branding,
    @SerializedName("counters")
    val counters: Counters,
    @SerializedName("department")
    val department: Department,
    @SerializedName("employer")
    val employer: Employer,
    @SerializedName("has_test")
    val hasTest: Boolean,
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterview,

    // Название вакансии
    @SerializedName("name")
    val name: String,

    @SerializedName("personal_data_resale")
    val personalDataResale: Boolean,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,
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
    val salary: Salary,

    // График работы
    @SerializedName("schedule")
    val schedule: Schedule,

    @SerializedName("show_logo_in_search")
    val showLogoInSearch: Boolean,

    // Описание вакансии
    @SerializedName("snippet")
    val snippet: Snippet,

    @SerializedName("sort_point_distance")
    val sortPointDistance: Double,

    // Тип вакансии
    @SerializedName("type")
    val type: Type,

    // Ссылка на вакансию
    @SerializedName("url")
    val url: String
)
