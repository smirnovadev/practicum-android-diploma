package ru.practicum.android.diploma.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import petrova.ola.playlistmaker.search.data.network.ApiService
import retrofit2.HttpException
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.AreasListResponse
import ru.practicum.android.diploma.data.dto.responses.CountriesListResponse
import ru.practicum.android.diploma.data.dto.responses.IndustriesListResponse
import ru.practicum.android.diploma.data.dto.requests.FindVacanciesRequest
import ru.practicum.android.diploma.data.dto.requests.AreaRequest
import ru.practicum.android.diploma.data.dto.requests.CountriesRequest
import ru.practicum.android.diploma.data.dto.requests.IndustriesRequest
import ru.practicum.android.diploma.data.dto.requests.VacancyByIdRequest
import java.io.IOException

/**
 * Error codes:
 * -2 - UNKNOWN THROWABLE
 * -1 - NO INTERNET
 * 100-500 - NETWORK ERROR CODES
 */

class RetrofitNetworkClient(
    private val apiService: ApiService,
    private val context: Context
) : NetworkClient {

    override suspend fun findVacancies(dto: FindVacanciesRequest): NetworkResponse {
        if (!isConnected()) {
            return NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                apiService.findVacancies(
                    query = dto.query,
                    salary = dto.salary,
                    page = dto.page,
                    amount = dto.amount,
                    onlyWithSalary = dto.onlyWithSalary,
                    industry = dto.industry,
                    area = dto.area
                ).apply { resultCode = SUCCESS_STATUS }
            } catch (e: HttpException) {
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                NetworkResponse().apply { resultCode = UNKNOWN_EXCEPTION }
            }
        }
    }

    override suspend fun getVacancyById(dto: VacancyByIdRequest): NetworkResponse {
        if (!isConnected()) {
            return NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                apiService.getVacancyById(dto.id).apply { resultCode = SUCCESS_STATUS }
            } catch (e: HttpException) {
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                NetworkResponse().apply { resultCode = UNKNOWN_EXCEPTION }
            }
        }
    }

    override suspend fun getCountries(dto: CountriesRequest): NetworkResponse {
        if (!isConnected()) {
            return NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                CountriesListResponse(
                    apiService.getCountries(dto.locale)
                ).apply { resultCode = SUCCESS_STATUS }
            } catch (e: HttpException) {
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                NetworkResponse().apply { resultCode = UNKNOWN_EXCEPTION }
            }
        }
    }

    override suspend fun getAreas(dto: AreaRequest): NetworkResponse {
        if (!isConnected()) {
            return NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                AreasListResponse(
                    apiService.getAreas(dto.locale)
                ).apply { resultCode = SUCCESS_STATUS }
            } catch (e: HttpException) {
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                NetworkResponse().apply { resultCode = UNKNOWN_EXCEPTION }
            }
        }
    }

    override suspend fun getIndustries(dto: IndustriesRequest): NetworkResponse {
        if (!isConnected()) {
            return NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                IndustriesListResponse(
                    apiService.getIndustries(dto.locale)
                ).apply { resultCode = SUCCESS_STATUS }
            } catch (e: HttpException) {
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                NetworkResponse().apply { resultCode = UNKNOWN_EXCEPTION }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        const val ERROR_NO_INTERNET = -1
        const val UNKNOWN_EXCEPTION = -2
        const val SUCCESS_STATUS = 200
    }
}
