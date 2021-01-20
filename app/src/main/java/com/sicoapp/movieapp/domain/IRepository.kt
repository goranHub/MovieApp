package com.sicoapp.movieapp.domain

import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.database.SmileyRatingEntity
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import io.reactivex.Observable

/**
 * @author ll4
 * @date 1/20/2021
 */
interface IRepository {

    fun fetchTopRatedMovies(pageId: Long): Observable<MovieResponse>

    fun fetchCrew(movieId: Long): Observable<Movie>

    fun fetchDetailsMovie(movieId: Long): Observable<Movie>

    fun fetchPopularMovies(pageId: Long): Observable<MovieResponse>

    fun fetchSearchMulti(query: String, pageId: Long): Observable<Multi>

    fun fetchSearchMultiTv(movieId: Long): Observable<TvResponse>

    fun insertData(itemId: Int, rating: Int)

    suspend fun getSaved(): List<SmileyRatingEntity>

    fun getMovieRatingDetails(itemId: Int): LiveData<SmileyRatingEntity>

    fun removeDataForThatItem(itemId: Int)

}