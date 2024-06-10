package ru.practicum.android.diploma.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.data.network.ApiService
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient

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
}

