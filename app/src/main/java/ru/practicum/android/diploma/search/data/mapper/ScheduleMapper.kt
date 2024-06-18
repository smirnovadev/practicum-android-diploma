package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.ScheduleDTO
import ru.practicum.android.diploma.search.domain.model.fields.Schedule

class ScheduleMapper {

    fun map(dto: ScheduleDTO?): Schedule {
        return Schedule(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
