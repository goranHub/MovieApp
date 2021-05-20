package com.sicoapp.movieapp.domain

import com.sicoapp.movieapp.data.database.*
import com.sicoapp.movieapp.data.remote.NetworkDataSource
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
    }

    fun getCrewByMovieId(movieId: Long): Observable<Movie> {
        return networkDataSource
            .getCrewByMovieId(movieId)
            .toObservable()
    }

    fun getMovieByID(movieId: Long): Observable<Movie> {
        return networkDataSource
            .getMovieByID(movieId)
            .toObservable()
    }

    fun getPopular(pageId: Long): Observable<MovieResponse> {
        return networkDataSource
            .getPopular(pageId)
            .toObservable()
    }

    fun getMulti(query: String): Observable<Multi> {
        return networkDataSource
            .getMulti(query)
            .toObservable()
    }

    fun getTvShowById(movieId: Long): Observable<TvResponse> {
        return networkDataSource
            .getTvShowById(movieId)
            .toObservable()
    }

    fun insertUser(user: User) {
        databaseDataSource.insertUser(user)
    }

    fun getAuthUserDB(): Observable<List<User>> {
        return databaseDataSource.getAuthUserDB().toObservable()
    }

    fun insertSmiley(itemId: Int, rating: Int) {
        databaseDataSource.insertSmiley(itemId, rating)
    }

    suspend fun getRatingsOfUser(curenntUSer : String):  List<UserWithRatings>  {
        return databaseDataSource.getRatingsOfUser(curenntUSer)
    }

    fun getSmileyByMovieId(itemId: Int): Single<Rating> {
        return databaseDataSource.getSmileyByMovieId(itemId)
    }

    fun deleteSmileyByMovieId(itemId: Int) {
        databaseDataSource.deleteSmileyByMovieId(itemId)
    }

    suspend fun insertUserMovieRatingCrossRef(crossRef: UserRatingsCrossRef){
        databaseDataSource.insertStudentSubjectCrossRef(crossRef)
    }
}
