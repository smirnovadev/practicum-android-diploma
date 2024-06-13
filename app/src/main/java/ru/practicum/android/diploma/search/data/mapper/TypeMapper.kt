package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.TypeDTO
import ru.practicum.android.diploma.search.domain.model.fields.Type

class TypeMapper {

    fun map(dto: TypeDTO?): Type {
        return Type(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
