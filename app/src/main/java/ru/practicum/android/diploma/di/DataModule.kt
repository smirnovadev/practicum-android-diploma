package ru.practicum.android.diploma.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.data.network.ApiService
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.job.data.ExternalNavigatorImpl
import ru.practicum.android.diploma.job.domain.ExternalNavigator
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper

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
        VacancyMapper()
    }

    factory<ExternalNavigator> {
        ExternalNavigatorImpl(androidContext())
    }

}

