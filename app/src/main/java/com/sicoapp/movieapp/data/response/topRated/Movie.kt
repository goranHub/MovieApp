package com.sicoapp.movieapp.data.response.topRated

import com.google.gson.annotations.SerializedName

data class Movie(
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    val genres: List<Genre>,
    val credits: Credits,
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    var popularity: String,
    @SerializedName("release_date")
    var releaseDate: String
    )
