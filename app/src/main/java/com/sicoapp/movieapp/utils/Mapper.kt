package com.sicoapp.movieapp.utils

import com.sicoapp.movieapp.data.database.User
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.data.remote.response.user.UserModel


fun Movie.mapToMovie() : Movie {
    return Movie(
        id = this.id,
        originalTitle = this.originalTitle,
        genres = this.genres,
        credits = this.credits,
        overview = this.overview,
        posterPath = this.posterPath,
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        title = this.title
    )
}

fun MovieResponse.mapToMovieResponse() : MovieResponse{
    return MovieResponse(
        page = this.page,
        results = this.results
    )
}

fun Multi.mapToMulti() : Multi {
    return Multi(
        page = this.page,
        results = this.results,
        total_pages = this.total_pages,
        total_results = this.total_results)
}

fun TvResponse.mapToTvResponse() : TvResponse{
    return TvResponse(
        first_air_date = this.first_air_date,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath)
}

fun UserFirebase.mapToUserEntity() : User {
    return  User(
        id = this.id,
        name = this.name,
        email = this.email,
        image = this.image,
        movieId = this.movieId,
        movieRating = this.movieRating,
        fcmToken = this.fcmToken
    )
}

fun UserFirebase.mapToUserModel() : UserModel {
    return  UserModel(
        id = this.id,
        name = this.name,
        email = this.email,
        image = this.image,
        movieId = this.movieId,
        movieRating = this.movieRating,
        fcmToken = this.fcmToken
    )
}
