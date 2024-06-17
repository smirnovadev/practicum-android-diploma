package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.AddressDTO
import ru.practicum.android.diploma.search.domain.model.fields.Address

class AddressMapper {

    fun map(dto: AddressDTO?): Address {
        return Address(
            building = dto?.building ?: EMPTY_STRING,
            city = dto?.city ?: EMPTY_STRING,
            description = dto?.description ?: EMPTY_STRING,
            lat = dto?.lat ?: EMPTY_DOUBLE,
            lng = dto?.lng ?: EMPTY_DOUBLE,
            street = dto?.street ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_DOUBLE = 0.0
    }
}
