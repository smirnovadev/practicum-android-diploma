package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.requests.FindVacanciesRequest
import ru.practicum.android.diploma.data.dto.responses.VacanciesFindResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val vacancyMapper: VacancyMapper
) : SearchRepository {
    override fun getVacancies(query: String): Flow<List<VacancySnippet>?> = flow {
        val vacancySearchRequest = FindVacanciesRequest(query)
        val response = networkClient.findVacancies(vacancySearchRequest)
        if (response.resultCode == 200) {
            val vacancyResponse = response as VacanciesFindResponse

            val resultVacancies = vacancyResponse.vacancies.map { dto ->
                vacancyMapper.map(dto)
            }
            emit(resultVacancies)
        } else {
            emit(null)
        }

    }
}
