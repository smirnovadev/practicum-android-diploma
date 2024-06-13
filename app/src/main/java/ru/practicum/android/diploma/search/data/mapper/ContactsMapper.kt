package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.fields.ContactsDTO
import ru.practicum.android.diploma.search.domain.model.fields.Contacts

class ContactsMapper(private val phoneMapper: PhoneMapper) {

    fun map(dto: ContactsDTO?): Contacts {
        return Contacts(
            email = dto?.email ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING,
            phones = dto?.phones?.map { dto -> phoneMapper.map(dto) } ?: listOf()
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
