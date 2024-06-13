package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.vacancies.VacanciesDTO
import ru.practicum.android.diploma.search.domain.model.Vacancies

class VacanciesMapper(private val vacancyMapper: VacancyMapper) {

    fun map(dto: VacanciesDTO?): Vacancies {
        return Vacancies(
            vacancies = dto?.vacancies?.map { dto -> vacancyMapper.map(dto) } ?: listOf(),
            found = dto?.found ?: EMPTY_INT,
            page = dto?.page ?: EMPTY_INT,
            pages = dto?.pages ?: EMPTY_INT,
            perPage = dto?.perPage ?: EMPTY_INT,
            fixes = dto?.fixes ?: EMPTY_STRING,
            suggests = dto?.suggests ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_INT = 0
    }
}
