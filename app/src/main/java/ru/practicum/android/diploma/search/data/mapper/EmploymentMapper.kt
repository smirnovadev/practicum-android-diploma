package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.EmploymentDTO
import ru.practicum.android.diploma.search.domain.model.fields.Employment

class EmploymentMapper {

    fun map(dto: EmploymentDTO?): Employment {
        return Employment(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING,
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
