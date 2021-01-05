package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.data.model.movie.Movie
import com.sicoapp.movieapp.data.model.movie.MovieResponse
import com.sicoapp.movieapp.data.model.tvShow.TvResponse
import io.reactivex.Flowable
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

    @GET("movie/{id}?&append_to_response=credits")
    fun loadCrewBy(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<Movie>

    @GET("search/movie?")
    fun searchMovie(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "query") searchTitle: String,
        @Query(value = "page") pageIndex: Int
    ) : Flowable<MovieResponse>

    @GET("search/tv?")
    fun searchTvShow(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "page") pageIndex: Int,
        @Query(value = "query") searchTitle: String,
    ) : Flowable<MovieResponse>

    @GET("search/multi?")
    fun searchMulti(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "query") searchTitle: String,
        @Query(value = "page") pageIndex: Int
    ) : Flowable<Multi>

    @GET("tv/{id}")
    fun getByTvID(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<TvResponse>

    @GET("movie/{id}")
    fun getByMovieID(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<Movie>

}
