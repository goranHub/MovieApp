package com.sicoapp.movieapp.data.model.response.movie

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String
)