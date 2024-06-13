package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.MetroStationDTO
import ru.practicum.android.diploma.search.domain.model.fields.MetroStation

class MetroStationMapper {

    fun map(dto: MetroStationDTO?): MetroStation {
        return MetroStation(
            lat = dto?.lat ?: EMPTY_DOUBLE,
            lineId = dto?.lineId ?: EMPTY_STRING,
            lineName = dto?.lineName ?: EMPTY_STRING,
            lng = dto?.lng ?: EMPTY_DOUBLE,
            stationId = dto?.stationId ?: EMPTY_STRING,
            stationName = dto?.stationName ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_DOUBLE = 0.0
    }
}
