package com.sicoapp.movieapp.domain

import com.sicoapp.movieapp.data.database.*
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.utils.mapToMovie
import com.sicoapp.movieapp.utils.mapToMovieResponse
import com.sicoapp.movieapp.utils.mapToMulti
import com.sicoapp.movieapp.utils.mapToTvResponse
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
