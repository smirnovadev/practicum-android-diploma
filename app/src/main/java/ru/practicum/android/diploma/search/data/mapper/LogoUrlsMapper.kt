package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.LogoUrlsDTO
import ru.practicum.android.diploma.search.domain.model.fields.LogoUrls

class LogoUrlsMapper {

    fun map(dto: LogoUrlsDTO?): LogoUrls {
        return LogoUrls(
            x90 = dto?.x90 ?: EMPTY_STRING,
            x240 = dto?.x240 ?: EMPTY_STRING,
            original = dto?.original ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
