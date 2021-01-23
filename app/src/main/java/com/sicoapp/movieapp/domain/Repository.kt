package com.sicoapp.movieapp.domain

import com.sicoapp.movieapp.data.database.DataBaseDataSource
import com.sicoapp.movieapp.data.database.SmileyRatingEntity
import com.sicoapp.movieapp.data.database.UserRatingCrossRef
import com.sicoapp.movieapp.data.database.UserWithRatings
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.data.remote.firebase.model.User
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author ll4
 * @date 1/7/2021
 */

class Repository(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DataBaseDataSource
) {

    fun getTopRated(pageId: Long): Observable<MovieResponse> {
        return networkDataSource
            .getTopRated(pageId)
            .toObservable()
            .map { movieResponse ->
                movieResponse.mapToMovieResponse()
            }
    }

    fun getCrewByMovieId(movieId: Long): Observable<Movie> {
        return networkDataSource
            .getCrewByMovieId(movieId)
            .toObservable()
            .map { movie ->
                movie.mapToMovie()
            }
    }

    fun getMovieByID(movieId: Long): Observable<Movie> {
        return networkDataSource
            .getMovieByID(movieId)
            .toObservable()
            .map { movie ->
                movie.mapToMovie()
            }
    }

    fun getPopular(pageId: Long): Observable<MovieResponse> {
        return networkDataSource
            .getPopular(pageId)
            .toObservable()
            .map { movieResponse ->
                movieResponse.mapToMovieResponse()
            }
    }

    fun getMulti(query: String, pageId: Long): Observable<Multi> {
        return networkDataSource
            .getMulti(query, pageId)
            .toObservable()
            .map { multi ->
                multi.mapToMulti()
            }
    }

    fun getTvShowById(movieId: Long): Observable<TvResponse> {
        return networkDataSource
            .getTvShowById(movieId)
            .toObservable()
            .map { tvResponse ->
                tvResponse.mapToTvResponse()
            }
    }

    fun insertUser(user: User) {
        databaseDataSource.insertUser(user)
    }

    fun getAuthUserDB(): Single<List<User>> {
        return databaseDataSource.getAuthUserDB()
    }

/*    fun insertSmiley(itemId: Int, rating: Int) {
        databaseDataSource.insertSmiley(itemId, rating)
    }  */

    fun insertUserMovieRatingCrossRef(itemId: Int, id: String, rating: Int) {
        databaseDataSource.insertUserMovieRatingCrossRef(itemId, id, rating)
    }

  /*  fun getSavedSmiley(): Single<List<SmileyRatingEntity>> {
        return databaseDataSource.getSavedSmileys()
    } */

    suspend fun getRatingsOfUser():  List<UserWithRatings>  {
        return databaseDataSource.getRatingsOfUser()
    }

    fun getSmileyByMovieId(itemId: Int): Single<SmileyRatingEntity> {
        return databaseDataSource.getSmileyByMovieId(itemId)
    }

    fun deleteSmileyByMovieId(itemId: Int) {
        databaseDataSource.deleteSmileyByMovieId(itemId)
    }

}
