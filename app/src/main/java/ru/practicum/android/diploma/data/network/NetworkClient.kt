package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.Response

interface NetworkClient {
    suspend fun findVacancies(dto: FindVacanciesRequest): Response
    suspend fun getVacancyById(dto: VacancyByIdRequest): Response
}
