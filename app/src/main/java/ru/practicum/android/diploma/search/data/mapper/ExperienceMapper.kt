package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.ExperienceDTO
import ru.practicum.android.diploma.search.domain.model.fields.Experience

class ExperienceMapper {

    fun map(dto: ExperienceDTO?): Experience {
        return Experience(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
