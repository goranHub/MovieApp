package com.sicoapp.movieapp.data.api

import com.sicoapp.movieapp.data.model.response.Movie
import com.sicoapp.movieapp.data.model.response.MovieResponse
import com.sicoapp.movieapp.utils.BindMovie
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author ll4
 * @date 12/6/2020
 */

interface MovieApiService {


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
    fun loadCrewBy(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Flowable<Movie>

    //https://api.themoviedb.org/3/search/movie?api_key=846c7e81abe66630e50ec975e045b52e&language=en-US&query=impos&page=1
    @GET("search/movie?")
    fun searchMovie(
        @Query(value = "api_key") apiKey : String,
        @Query(value = "query") searchTitle : String,
        @Query(value = "page") pageIndex : Int
    ) : Flowable<MovieResponse>
}

// https://api.themoviedb.org/3/search/movie?&apiKey=846c7e81abe66630e50ec975e045b52e&query=FOCUS&page=1
// https://api.themoviedb.org/3/search/movie?api_key=846c7e81abe66630e50ec975e045b52e&query=FOCUS&page=1