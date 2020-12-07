package com.sicoapp.movieapp.data.response.topRated

import androidx.databinding.BaseObservable

data class TopRated(
    var adult: Boolean,
    var backdrop_path: String,
    var belongs_to_collection: Any,
    var budget: Int,
    var genres: List<Genre>,
    var homepage: String,
    var id: Int,
    var imdb_id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    val popularity: Double,
    var poster_path: String,
    val production_companies: List<ProductionCompany>
   /* val production_countries: List<ProductionCountry>,
    var release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int*/
): BaseObservable()