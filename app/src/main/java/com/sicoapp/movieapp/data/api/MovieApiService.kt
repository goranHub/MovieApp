package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.data.response.MovieResponse
import com.sicoapp.movieapp.utils.BASE_URL
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    ): Flowable<MovieResponse>

    @GET("movie/popular")
    fun loadPopular(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Flowable<MovieResponse>

    @GET("movie/{id}")
    fun getByID(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun loadCrewById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>


    @GET("movie/{id}?&append_to_response=credits")
    fun loadCrewBy(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<Movie>

}