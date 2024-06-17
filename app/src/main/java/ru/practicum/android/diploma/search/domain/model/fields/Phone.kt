package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class Phone(
    val city: String?,
    val comment: String?,
    val country: String?,
    val number: String?
) : Serializable
