package com.androidavanzado.popcorn.di

import com.androidavanzado.popcorn.repository.TheMovieDBRepository
import com.androidavanzado.popcorn.api.NetworkModule
import com.androidavanzado.popcorn.api.TheMovieDBInterceptor
import com.androidavanzado.popcorn.ui.movies.MovieListFragment
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(theMovieDBRepository: TheMovieDBRepository)
    fun inject(okHttpClient: OkHttpClient)
    fun inject(theMovieDBInterceptor: TheMovieDBInterceptor)
}