package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
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
    fun loadTopRated(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Call<AboveTopRated>

    @GET("movie/{id}")
    fun loadById(@Path("id") id: Int,
                 @Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun loadCrewById(@Path("id") id: Int,
                     @Query("api_key") apiKey: String): Call<Movie>

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