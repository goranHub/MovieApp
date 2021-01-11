package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.data.model.response.multi.Multi
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author ll4
 * @date 1/7/2021
 */
interface ApiServisFlow {


    @GET("movie/top_rated")
    fun loadTopRated(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Single<MovieResponse>


    @GET("movie/popular")
    fun loadPopular(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Single<MovieResponse>

    @GET("search/multi?")
    fun searchMulti(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "query") searchTitle: String,
        @Query(value = "page") pageIndex: Int
    ) : Single<Multi>
}