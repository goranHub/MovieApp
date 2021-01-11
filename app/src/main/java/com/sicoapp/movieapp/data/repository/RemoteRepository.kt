package com.sicoapp.movieapp.data.repository

import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.data.model.response.tvShow.TvResponse
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class RemoteRepository @Inject constructor(
    private val api: ApiServisFlow
) {
    fun fetchTopMovies(pageId: Int): Single<MovieResponse> {
        return api.loadTopRated(API_KEY, pageId.toString())
    }

    fun fetchPopularMovies(pageId: Int): Single<MovieResponse> {
        return api.loadPopular(API_KEY, pageId.toString())
    }

    fun fetchSearchMultiMovie(query: String, pageId: Long): Single<Multi> {
        return api.searchMulti(API_KEY, query, pageId)
    }

    fun fetchSearchMultiMovie(movieId: Long): Single<Movie> {
        return api.getByMovieID(movieId, API_KEY)
    }

    fun fetchSearchMultiTv(movieId: Long): Single<TvResponse> {
        return api.getByTvID(movieId, API_KEY)
    }

    fun fetchCrew(movieId: Long): Single<Movie> {
        return api.loadCrewBy(movieId, API_KEY)
    }
}
