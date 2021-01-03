package com.sicoapp.movieapp.data.response

/**
 * @author ll4
 * @date 12/6/2020
 */
data class MovieResponse (
    val page: Int,
    val results: MutableList<Movie>,
    val totalResults: Int,
    val totalPages :Int
)