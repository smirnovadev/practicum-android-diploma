package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.data.dto.requests.FindVacanciesRequest
import ru.practicum.android.diploma.data.dto.responses.VacanciesFindResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.Resource
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.VacancySnippet
import java.io.IOException

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val vacancyMapper: VacancyMapper
) : SearchRepository {

    override fun getVacancies(query: String): Flow<Resource<List<VacancySnippet>>> = flow {
        val vacancySearchRequest = FindVacanciesRequest(query)
        val response = networkClient.findVacancies(vacancySearchRequest)

        if (response.resultCode == RetrofitNetworkClient.SUCCESS_STATUS) {
            val vacancyResponse = response as VacanciesFindResponse
            val resultVacancies = vacancyResponse.vacancies.map { dto ->
                vacancyMapper.map(dto)
            }
            emit(Resource.Success(resultVacancies))
        } else {
            emit(Resource.Error(response.resultCode))
        }
    }.catch { e ->
        val errorCode = when (e) {
            is IOException -> RetrofitNetworkClient.ERROR_NO_INTERNET
            is HttpException -> e.code()
            else -> RetrofitNetworkClient.IO_EXCEPTION
        }
        emit(Resource.Error(errorCode))
    }
}
