package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.VacancyAreaDTO
import ru.practicum.android.diploma.search.domain.model.fields.VacancyArea

class VacancyAreaMapper {

    fun map(dto: VacancyAreaDTO?): VacancyArea {
        return VacancyArea(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING,
            url = dto?.url ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
