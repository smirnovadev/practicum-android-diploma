package ru.practicum.android.diploma.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.requests.AreaRequest
import ru.practicum.android.diploma.data.dto.requests.CountriesRequest
import ru.practicum.android.diploma.data.dto.requests.VacanciesSearchRequest
import ru.practicum.android.diploma.data.dto.requests.IndustriesRequest
import ru.practicum.android.diploma.data.dto.requests.VacancyByIdRequest
import ru.practicum.android.diploma.data.dto.responses.AreasListResponse
import ru.practicum.android.diploma.data.dto.responses.CountriesListResponse
import ru.practicum.android.diploma.data.dto.responses.IndustriesListResponse
import java.io.IOException

/**
 * Error codes:
 * -1 - IO EXCEPTION
 * 100-500 - NETWORK ERROR CODES
 */

class RetrofitNetworkClient(
    private val apiService: ApiService,
    private val context: Context
) : NetworkClient {

    override suspend fun findVacancies(dto: VacanciesSearchRequest): NetworkResponse {
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
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = IO_EXCEPTION }
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
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = IO_EXCEPTION }
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
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = IO_EXCEPTION }
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
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = IO_EXCEPTION }
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
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = e.code() }
            } catch (e: IOException) {
                Log.e(TAG, e.stackTraceToString())
                NetworkResponse().apply { resultCode = IO_EXCEPTION }
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
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        return false
    }

    companion object {
        const val ERROR_NO_INTERNET = -1
        const val IO_EXCEPTION = -2
        const val SUCCESS_STATUS = 200
        const val TAG = "DIPLOM_RETROFIT_CLIENT"
    }
}
