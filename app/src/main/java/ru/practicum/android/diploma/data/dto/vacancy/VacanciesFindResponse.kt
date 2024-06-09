package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

/**
 * https://api.hh.ru/openapi/redoc#tag/Poisk-vakansij/operation/get-vacancies
 */

data class VacanciesFindResponse(
    @SerializedName("items")
    val vacancies: List<SearchVacanciesUnit>,
    @SerializedName("found")
    val found: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("fixes")
    val fixes: String?,
    @SerializedName("suggests")
    val suggests: String?
)
