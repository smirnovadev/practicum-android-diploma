package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.EmployerDTO
import ru.practicum.android.diploma.search.domain.model.fields.Employer

class EmployerMapper(private val logoUrlsMapper: LogoUrlsMapper) {

    fun map(dto: EmployerDTO?): Employer {
        return Employer(
            accreditedItEmployer = dto?.accreditedItEmployer ?: EMPTY_BOOLEAN,
            alternateUrl = dto?.alternateUrl ?: EMPTY_STRING,
            id = dto?.id ?: EMPTY_STRING,
            logoUrls = logoUrlsMapper.map(dto?.logoUrls),
            name = dto?.name ?: EMPTY_STRING,
            trusted = dto?.trusted ?: EMPTY_BOOLEAN,
            url = dto?.url ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_BOOLEAN = false
    }
}
