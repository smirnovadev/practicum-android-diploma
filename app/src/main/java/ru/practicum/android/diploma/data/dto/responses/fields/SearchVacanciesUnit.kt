package ru.practicum.android.diploma.data.dto.responses.fields

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

    // Число откликов на вакансию
    @SerializedName("counters")
    val counters: Counters,

    // Департамент
    @SerializedName("department")
    val department: Department,

    // Поля работодателя в вакансии
    @SerializedName("employer")
    val employer: Employer,

    // Есть ли тестовое задание
    @SerializedName("has_test")
    val hasTest: Boolean,

    // Интервью о жизни в компании
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterview,

    // Название вакансии
    @SerializedName("name")
    val name: String,

    // Список профессиональных ролей
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,

    // Дата и время публикации вакансии
    @SerializedName("published_at")
    val publishedAt: String,

    // Связи соискателя с вакансией
    @SerializedName("relations")
    val relations: List<Any>,

    // Обязательно ли заполнять сообщение при отклике на вакансию
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean,

    // URL отклика для прямых вакансий
    @SerializedName("response_url")
    val responseUrl: Any,

    // Зарплата
    @SerializedName("salary")
    val salary: Salary,

    // График работы
    @SerializedName("schedule")
    val schedule: Schedule,

    // Отображать ли лого для вакансии в поисковой выдаче
    @SerializedName("show_logo_in_search")
    val showLogoInSearch: Boolean,

    // Описание вакансии
    @SerializedName("snippet")
    val snippet: Snippet,

    // Расстояние между центром сортировки и указанным в вакансии адресом
    @SerializedName("sort_point_distance")
    val sortPointDistance: Double,

    // Тип вакансии
    @SerializedName("type")
    val type: Type,

    // Ссылка на вакансию
    @SerializedName("url")
    val url: String
)
