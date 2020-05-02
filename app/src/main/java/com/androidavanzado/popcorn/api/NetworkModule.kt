package com.androidavanzado.popcorn.api

import com.androidavanzado.popcorn.common.Constantes
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constantes.TMDBAPI_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(theMovieDBInterceptor: TheMovieDBInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(theMovieDBInterceptor)
            connectTimeout(Constantes.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Singleton
    @Provides
    fun provideTheMovieDBRetrofitService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): TheMovieDBService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TheMovieDBService::class.java)
    }

}