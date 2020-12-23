package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BASE_URL
import retrofit2.Call
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


    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Response<AboveTopRated>

    @GET("movie/{id}")
    fun getAllMyMoviesById(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun getCrew(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}