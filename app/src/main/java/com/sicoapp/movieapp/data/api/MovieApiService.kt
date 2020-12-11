package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.data.response.topRated.Credits
import com.sicoapp.movieapp.data.response.topRated.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * @author ll4
 * @date 12/6/2020
 */
interface MovieApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<AboveTopRated>


    @GET("movie/{id}")
    fun getAllMyMovies(@Path("id") id:Int, @Query("api_key") apiKey: String) : Call<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun getCrew(@Path("id") id:Int, @Query("api_key") apiKey: String) : Call<Movie>


}