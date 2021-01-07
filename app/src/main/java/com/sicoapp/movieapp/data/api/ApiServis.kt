package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author ll4
 * @date 1/7/2021
 */
interface ApiServis {


    @GET("movie/top_rated")
    suspend fun loadTopRated(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): MovieResponse

}