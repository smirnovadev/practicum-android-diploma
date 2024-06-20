package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class VacancyArea(
    val id: String?,
    val name: String?,
    val url: String?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
