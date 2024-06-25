package ru.practicum.android.diploma.search.domain.model.fields

data class Area(
    val id: Int,
    val name: String,
    val parent: String? = null,
)
