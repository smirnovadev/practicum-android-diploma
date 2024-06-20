package ru.practicum.android.diploma.job.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.requests.VacancyByIdRequest
import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.job.data.mapper.ResponseToVacancyMapper
import ru.practicum.android.diploma.job.domain.JobRepository
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

class JobRepositoryImpl(
    private val networkClient: NetworkClient,
    private val responseToVacancyMapper: ResponseToVacancyMapper,
) : JobRepository {
    override fun getVacancyById(id: String): Flow<Resource<Vacancy>> = flow {
        val vacancyIdRequest = VacancyByIdRequest(id = id)
        val response = networkClient.getVacancyById(vacancyIdRequest)

        when (response.resultCode) {
            ERROR_NO_INTERNET -> emit(Resource.Error(ERROR_NO_INTERNET))
            IO_EXCEPTION -> emit(Resource.Error(IO_EXCEPTION))
            SUCCESS_STATUS -> {
                val vacancyIdResponse = response as VacancyByIdResponse
                if (vacancyIdResponse != null) {
                    val resultVacancyId = responseToVacancyMapper.map(vacancyIdResponse)
                    emit(Resource.Success(resultVacancyId))
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
    }
}
