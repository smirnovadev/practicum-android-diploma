package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class Employer(
    val accreditedItEmployer: Boolean?,
    val alternateUrl: String?,
    val id: String?,
    val logoUrls: LogoUrls?,
    val name: String?,
    val trusted: Boolean?,
    val url: String?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
