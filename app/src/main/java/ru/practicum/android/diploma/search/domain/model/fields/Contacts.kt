package ru.practicum.android.diploma.search.domain.model.fields

data class Contacts(
    val email: String?,
    val name: String?,
    val phones: List<Phone?>
)
