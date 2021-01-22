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
    private val movieService: MovieServis
) {

    fun fetchTopRatedMovies(pageId: Long): Single<MovieResponse> {
        return movieService.loadTopRated(API_KEY, pageId.toString())
    }

    fun fetchPopularMovies(pageId: Long): Single<MovieResponse> {
        return movieService.loadPopular(API_KEY, pageId.toString())
    }

    fun fetchDetailsMovie(query: String, pageId: Long): Single<Multi> {
        return movieService.searchMulti(API_KEY, query, pageId)
    }

    fun fetchDetailsMovie(movieId: Long): Single<Movie> {
        return movieService.getByMovieID(movieId, API_KEY)
    }

    fun fetchSearchMultiTv(movieId: Long): Single<TvResponse> {
        return movieService.getByTvID(movieId, API_KEY)
    }

    fun fetchCrew(movieId: Long): Single<Movie> {
        return movieService.loadCrewBy(movieId, API_KEY)
    }
}