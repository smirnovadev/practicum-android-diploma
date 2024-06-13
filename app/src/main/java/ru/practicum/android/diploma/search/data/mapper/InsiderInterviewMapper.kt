package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.InsiderInterviewDTO
import ru.practicum.android.diploma.search.domain.model.fields.InsiderInterview

class InsiderInterviewMapper {

    fun map(dto: InsiderInterviewDTO?): InsiderInterview {
        return InsiderInterview(
            id = dto?.id ?: EMPTY_STRING,
            url = dto?.url ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
