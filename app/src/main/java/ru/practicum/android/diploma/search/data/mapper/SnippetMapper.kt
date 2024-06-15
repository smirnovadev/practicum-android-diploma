package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.SnippetDTO
import ru.practicum.android.diploma.search.domain.model.fields.Snippet

class SnippetMapper {

    fun map(dto: SnippetDTO?): Snippet {
        return Snippet(
            requirement = dto?.requirement ?: EMPTY_STRING,
            responsibility = dto?.responsibility ?: EMPTY_STRING,
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
