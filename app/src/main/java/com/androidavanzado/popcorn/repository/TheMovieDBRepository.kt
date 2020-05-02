package com.androidavanzado.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.androidavanzado.popcorn.di.MyApp
import com.androidavanzado.popcorn.api.TheMovieDBService
import com.androidavanzado.popcorn.api.response.Movie
import com.androidavanzado.popcorn.api.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TheMovieDBRepository @Inject constructor(var theMovieDBService: TheMovieDBService) {
    var popularMovies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    init {
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>> {

        val call: Call<PopularMoviesResponse>? = theMovieDBService.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesResponse> {
            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
                if(response.isSuccessful) {
                    popularMovies.value = response.body()?.results
                }
            }
        })

        return popularMovies
    }

}