package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.requests.AreaRequest
import ru.practicum.android.diploma.data.dto.requests.CountriesRequest
import ru.practicum.android.diploma.data.dto.requests.IndustriesRequest
import ru.practicum.android.diploma.data.dto.requests.VacanciesSearchRequest
import ru.practicum.android.diploma.data.dto.requests.VacancyByIdRequest

interface NetworkClient {
    suspend fun findVacancies(dto: VacanciesSearchRequest): NetworkResponse
    suspend fun getVacancyById(dto: VacancyByIdRequest): NetworkResponse
    suspend fun getCountries(dto: CountriesRequest): NetworkResponse
    suspend fun getAreas(dto: AreaRequest): NetworkResponse
    suspend fun getIndustries(dto: IndustriesRequest): NetworkResponse
}
