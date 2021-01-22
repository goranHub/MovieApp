package com.sicoapp.movieapp.domain

import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.database.DataBaseDataSource
import com.sicoapp.movieapp.data.database.SmileyRatingEntity
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.data.remote.firebase.model.User
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import io.reactivex.Observable

/**
 * @author ll4
 * @date 1/7/2021
 */

class Repository(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DataBaseDataSource
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

     fun insertDataUser(user: User) {
        databaseDataSource.insertDataUser(user)
    }

     suspend fun getSavedUsers():  List<User> {
        return databaseDataSource.getSavedUsers()
    }

     fun insertData(itemId: Int, rating: Int) {
        databaseDataSource.insertData(itemId, rating)
    }

     suspend fun getSaved(): List<SmileyRatingEntity> {
        return databaseDataSource.getSavedSmileys()
    }

     fun getMovieRatingDetails(itemId: Int): LiveData<SmileyRatingEntity> {
        return databaseDataSource.getMovieRatingDetails(itemId)
    }

     fun removeDataForThatItem(itemId: Int) {
        return databaseDataSource.removeDataForThatItem(itemId)
    }

    fun fromDatabase(){

    }
}
