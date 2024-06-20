package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class Schedule(
    val id: String?,
    val name: String?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
