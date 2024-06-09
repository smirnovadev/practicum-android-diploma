package petrova.ola.playlistmaker.search.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.vacancy.VacanciesFindResponse

interface ApiService {

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: YP HH Diploma (evstigneevstas@gmail.com)"
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(@Path("vacancy_id") id: String): Response

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

    // TODO:
    // Список регионов / стран
    // Список индустрий
}
