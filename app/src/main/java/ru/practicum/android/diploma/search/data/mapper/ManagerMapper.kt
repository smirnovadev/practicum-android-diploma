package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.ManagerDTO
import ru.practicum.android.diploma.search.domain.model.fields.Manager

class ManagerMapper {

    fun map(dto: ManagerDTO?): Manager {
        return Manager(id = dto?.id ?: EMPTY_STRING)
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
