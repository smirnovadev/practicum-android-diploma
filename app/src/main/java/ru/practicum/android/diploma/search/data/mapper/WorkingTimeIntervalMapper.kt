package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.WorkingTimeIntervalDTO
import ru.practicum.android.diploma.search.domain.model.fields.WorkingTimeInterval

class WorkingTimeIntervalMapper {

    fun map(dto: WorkingTimeIntervalDTO?): WorkingTimeInterval {
        return WorkingTimeInterval(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
