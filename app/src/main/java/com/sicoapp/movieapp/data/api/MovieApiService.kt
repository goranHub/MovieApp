package com.sicoapp.movieapp.data.api

import android.util.Log
import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.utils.DetailsObserver
import com.sicoapp.movieapp.ui.movie.list.ListItemViewModel
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BASE_URL
import com.sicoapp.movieapp.utils.Injection
import com.sicoapp.movieapp.utils.URL_IMAGE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author ll4
 * @date 12/6/2020
 */



interface MovieApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Call<AboveTopRated>

    @GET("movie/{id}")
    fun getAllMyMoviesById(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun getCrew(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    companion object {

        fun getClient(): MovieApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApiService::class.java)
        }
    }
}