package com.sicoapp.movieapp.data.remote

import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/20/2021
 */

class NetworkDataSource @Inject constructor(
    private val movieService: MovieDao
) {

    fun getTopRated(pageId: Long): Single<MovieResponse> {
        return movieService.getTopRated(API_KEY, pageId.toString())
    }

    fun getPopular(pageId: Long): Single<MovieResponse> {
        return movieService.getPopular(API_KEY, pageId.toString())
    }

    fun getMulti(query: String): Single<Multi> {
        return movieService.getMulti(API_KEY, query)
    }

    fun getMovieByID(movieId: Long): Single<Movie> {
        return movieService.getMovieByID(movieId, API_KEY)
    }

    fun getTvShowById(movieId: Long): Single<TvResponse> {
        return movieService.getTvShowById(movieId, API_KEY)
    }

    fun getCrewByMovieId(movieId: Long): Single<Movie> {
        return movieService.getCrewByMovieId(movieId, API_KEY)
    }
}