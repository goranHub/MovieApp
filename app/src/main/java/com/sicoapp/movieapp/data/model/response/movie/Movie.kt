package com.sicoapp.movieapp.data.model.response.movie

import com.google.gson.annotations.SerializedName


interface IMovie {
    val id: Int

    val originalTitle: String
    val genres: List<Genre>
    val credits: Credits
    val overview: String

    val posterPath: String
    val popularity: String

    val releaseDate: String

    val title: String?
}

data class Movie(
    override val id: Int,
    @SerializedName("original_title")
    override val originalTitle: String,
    override val genres: List<Genre>,
    override val credits: Credits,
    override val overview: String,
    @SerializedName("poster_path")
    override val posterPath: String,
    override val popularity: String,
    @SerializedName("release_date")
    override val releaseDate: String,
    @SerializedName("title")
    override val title: String?
    ) : IMovie
