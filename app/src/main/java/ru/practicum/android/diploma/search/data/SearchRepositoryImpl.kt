package ru.practicum.android.diploma.search.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.requests.VacanciesSearchRequest
import ru.practicum.android.diploma.data.dto.responses.VacanciesSearchResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.search.data.mapper.ResponseToVacanciesMapper
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val responseToVacanciesMapper: ResponseToVacanciesMapper
) : SearchRepository {

    override fun getVacancies(
        query: String,
        page: Int,
        salary: Int?,
        salaryFlag: Boolean,
        industry: String?,
        area: String?
    ): Flow<Resource<Vacancies>> = flow {
        val vacancySearchRequest = VacanciesSearchRequest(
            query = query,
            page = page,
            salary = salary,
            onlyWithSalary = salaryFlag,
            industry = industry,
            area = area
        )
        Log.d(TAG_SEARCH_REQUEST, "$vacancySearchRequest")
        val response = networkClient.findVacancies(vacancySearchRequest)

        when (response.resultCode) {
            ERROR_NO_INTERNET -> emit(Resource.Error(ERROR_NO_INTERNET))
            IO_EXCEPTION -> emit(Resource.Error(IO_EXCEPTION))
            SUCCESS_STATUS -> {
                val vacancyResponse = response as VacanciesSearchResponse
                Log.d(TAG_SEARCH_RESPONSE, "$vacancyResponse")
                if (vacancyResponse != null) {
                    val resultVacancies = responseToVacanciesMapper.map(vacancyResponse)
                    emit(Resource.Success(resultVacancies))
                } else {
                    emit(Resource.Error(NOTHING_FOUND))
                }
            }

            else -> emit(Resource.Error(response.resultCode))
        }
    }

    companion object {
        private const val ERROR_NO_INTERNET = -1
        private const val IO_EXCEPTION = -2
        private const val SUCCESS_STATUS = 200
        private const val NOTHING_FOUND = 404
        const val TAG_SEARCH_REQUEST = "Search Request"
        const val TAG_SEARCH_RESPONSE = "Search Response"
    }
}
