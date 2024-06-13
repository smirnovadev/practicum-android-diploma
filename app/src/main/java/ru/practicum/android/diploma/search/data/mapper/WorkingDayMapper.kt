package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.WorkingDayDTO
import ru.practicum.android.diploma.search.domain.model.fields.WorkingDay

class WorkingDayMapper {

    fun map(dto: WorkingDayDTO?): WorkingDay {
        return WorkingDay(
            id = dto?.id ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
