package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.data.dto.responses.VacanciesSearchResponse
import ru.practicum.android.diploma.search.domain.model.Vacancies

class ResponseToVacanciesMapper(private val vacancyMapper: VacancyMapper) {

    fun map(response: VacanciesSearchResponse?): Vacancies {
        return Vacancies(
            vacancies = response?.vacancies?.map { dto -> vacancyMapper.map(dto) } ?: listOf(),
            found = response?.found ?: EMPTY_INT,
            page = response?.page ?: EMPTY_INT,
            pages = response?.pages ?: EMPTY_INT,
            perPage = response?.perPage ?: EMPTY_INT,
            fixes = response?.fixes ?: EMPTY_STRING,
            suggests = response?.suggests ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_INT = 0
    }
}
