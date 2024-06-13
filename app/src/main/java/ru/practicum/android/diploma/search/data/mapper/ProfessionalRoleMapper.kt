package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.ProfessionalRoleDTO
import ru.practicum.android.diploma.search.domain.model.fields.ProfessionalRole

class ProfessionalRoleMapper {

    fun map(dto: ProfessionalRoleDTO?): ProfessionalRole {
        return ProfessionalRole(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
