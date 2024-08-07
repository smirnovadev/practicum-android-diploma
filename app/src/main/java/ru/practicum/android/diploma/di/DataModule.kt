package ru.practicum.android.diploma.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.data.network.ApiService
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.filters.data.FiltersLocalStorageImpl
import ru.practicum.android.diploma.job.data.ExternalNavigatorImpl
import ru.practicum.android.diploma.job.data.mapper.ResponseToVacancyMapper
import ru.practicum.android.diploma.job.domain.ExternalNavigator
import ru.practicum.android.diploma.search.data.mapper.AddressMapper
import ru.practicum.android.diploma.search.data.mapper.AreaMapper
import ru.practicum.android.diploma.search.data.mapper.ContactsMapper
import ru.practicum.android.diploma.search.data.mapper.EmployerMapper
import ru.practicum.android.diploma.search.data.mapper.EmploymentMapper
import ru.practicum.android.diploma.search.data.mapper.ExperienceMapper
import ru.practicum.android.diploma.search.data.mapper.IndustryMapper
import ru.practicum.android.diploma.search.data.mapper.KeySkillMapper
import ru.practicum.android.diploma.search.data.mapper.LogoUrlsMapper
import ru.practicum.android.diploma.search.data.mapper.PhoneMapper
import ru.practicum.android.diploma.search.data.mapper.ResponseToVacanciesMapper
import ru.practicum.android.diploma.search.data.mapper.ScheduleMapper
import ru.practicum.android.diploma.search.data.mapper.VacanciesMapper
import ru.practicum.android.diploma.search.data.mapper.VacancyAreaMapper
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper
import ru.practicum.android.diploma.util.MapperContainer

val dataModule = module {
    single(named("baseUrl")) {
        "https://api.hh.ru"
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(
                get<String>(named("baseUrl"))
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    factory { Gson() }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

    single<SharedPreferences> {
        androidContext()
            .getSharedPreferences("key_for_job_search_apps", Context.MODE_PRIVATE)
    }

    single<VacancyMapper> {
        VacancyMapper(
            get(),
        )
    }

    factory<ExternalNavigator> {
        ExternalNavigatorImpl(androidContext())
    }

    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<AddressMapper> {
        AddressMapper()
    }

    single<ContactsMapper> {
        ContactsMapper(get())
    }

    single<EmployerMapper> {
        EmployerMapper(get())
    }

    single<EmploymentMapper> {
        EmploymentMapper()
    }

    single<ExperienceMapper> {
        ExperienceMapper()
    }

    single<KeySkillMapper> {
        KeySkillMapper()
    }

    single<LogoUrlsMapper> {
        LogoUrlsMapper()
    }

    single<PhoneMapper> {
        PhoneMapper()
    }

    single<ScheduleMapper> {
        ScheduleMapper()
    }

    single<VacancyAreaMapper> {
        VacancyAreaMapper()
    }

    single<VacanciesMapper> {
        VacanciesMapper(get())
    }

    single<ResponseToVacanciesMapper> {
        ResponseToVacanciesMapper(get())
    }

    single<IndustryMapper> {
        IndustryMapper()
    }

    single<AreaMapper> {
        AreaMapper()
    }

    single<FiltersLocalStorageImpl> {
        FiltersLocalStorageImpl(get(), get())
    }

    single<ResponseToVacancyMapper> {
        ResponseToVacancyMapper(
            get()
        )
    }

    single<MapperContainer> {
        MapperContainer(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}
