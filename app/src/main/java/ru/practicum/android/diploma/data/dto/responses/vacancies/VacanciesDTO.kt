package ru.practicum.android.diploma.data.dto.responses.vacancies

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.NetworkResponse

data class VacanciesDTO(
    @SerializedName("items")
    val vacancies: List<VacancyDTO>?,
    @SerializedName("found")
    val found: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("fixes")
    val fixes: String?,
    @SerializedName("suggests")
    val suggests: String?
) : NetworkResponse()
