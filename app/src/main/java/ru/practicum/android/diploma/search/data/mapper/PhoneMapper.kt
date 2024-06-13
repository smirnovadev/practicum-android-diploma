package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.PhoneDTO
import ru.practicum.android.diploma.search.domain.model.fields.Phone

class PhoneMapper {

    fun map(dto: PhoneDTO?): Phone {
        return Phone(
            city = dto?.city ?: EMPTY_STRING,
            comment = dto?.comment ?: EMPTY_STRING,
            country = dto?.country ?: EMPTY_STRING,
            number = dto?.number ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
