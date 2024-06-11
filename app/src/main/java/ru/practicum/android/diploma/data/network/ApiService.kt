package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.dto.responses.VacanciesFindResponse
import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasList
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesList

interface ApiService {
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: YP HH Diploma (evstigneevstas@gmail.com)"
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(@Path("vacancy_id") id: String): VacancyByIdResponse

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: YP HH Diploma (evstigneevstas@gmail.com)"
    )
    @GET("/vacancies")
    suspend fun findVacancies(
        @Query("text") query: String,
        @Query("salary") salary: String? = null,
        @Query("page") page: Int = 0,
        @Query("per_page") amount: Int = 20,
        @Query("only_with_salary") onlyWithSalary: Boolean = false,
        @Query("industry") industry: String? = null,
        @Query("area") area: String? = null
    ): VacanciesFindResponse

    @GET("/areas/countries")
    suspend fun getCountries(
        @Query("locale") locale: String = "RU"
    ): AreasList

    @GET("/areas")
    suspend fun getAreas(
        @Query("locale") locale: String = "RU"
    ): AreasList

    @GET("/salary_statistics/dictionaries/salary_industries")
    suspend fun getIndustries(
        @Query("locale") locale: String = "RU"
    ): IndustriesList
}
