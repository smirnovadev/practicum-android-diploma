package ru.practicum.android.diploma.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<SharedPreferences> {
        androidContext()
            .getSharedPreferences("key_for_job_search_apps", Context.MODE_PRIVATE)
    }
}