package petrova.ola.playlistmaker.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import petrova.ola.playlistmaker.search.data.network.ApiService
import petrova.ola.playlistmaker.search.data.network.RetrofitNetworkClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.data.network.NetworkClient

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

}
