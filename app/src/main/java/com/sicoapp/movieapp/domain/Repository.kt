package com.sicoapp.movieapp.domain

import com.sicoapp.movieapp.data.database.DataBaseDataSource
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    val databaseDataSource: DataBaseDataSource
) {
    fun fetchTopRatedMovies(pageId: Long): Observable<MovieResponse> {
        return networkDataSource
            .fetchTopRatedMovies(pageId)
            .toObservable()
            .map { movieResponse ->
                movieResponse.mapToMovieResponse()
            }
    }

    fun fetchCrew(movieId: Long): Observable<Movie> {
        return networkDataSource
            .fetchCrew(movieId)
            .toObservable()
            .map { movie ->
                movie.mapToMovie()
            }
    }

    fun fetchDetailsMovie(movieId: Long): Observable<Movie> {
        return networkDataSource
            .fetchDetailsMovie(movieId)
            .toObservable()
            .map { movie ->
                movie.mapToMovie()
            }
    }

    fun fetchPopularMovies(pageId: Long): Observable<MovieResponse> {
        return networkDataSource
            .fetchPopularMovies(pageId)
            .toObservable()
            .map { movieResponse ->
                movieResponse.mapToMovieResponse()
            }
    }

    fun fetchSearchMulti(query: String, pageId: Long): Observable<Multi> {
        return networkDataSource
            .fetchDetailsMovie(query, pageId)
            .toObservable()
            .map { multi ->
                multi.mapToMulti()
            }
    }

    fun fetchSearchMultiTv(movieId: Long): Observable<TvResponse> {
        return networkDataSource
            .fetchSearchMultiTv(movieId)
            .toObservable()
            .map { tvResponse ->
                tvResponse.mapToTvResponse()
            }
    }
}
