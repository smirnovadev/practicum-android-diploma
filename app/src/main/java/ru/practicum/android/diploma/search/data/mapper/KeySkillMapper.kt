package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.KeySkillDTO
import ru.practicum.android.diploma.search.domain.model.fields.KeySkill

class KeySkillMapper {

    fun map(dto: KeySkillDTO?): KeySkill {
        return KeySkill(name = dto?.name ?: EMPTY_STRING)
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
