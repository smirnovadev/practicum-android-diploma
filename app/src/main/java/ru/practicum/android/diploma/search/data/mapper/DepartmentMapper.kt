package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.DepartmentDTO
import ru.practicum.android.diploma.search.domain.model.fields.Department

class DepartmentMapper {

    fun map(dto: DepartmentDTO?): Department {
        return Department(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
