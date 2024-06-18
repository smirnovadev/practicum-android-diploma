package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class Contacts(
    val email: String?,
    val name: String?,
    val phones: List<Phone?>
) : Serializable
