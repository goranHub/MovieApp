package com.sicoapp.movieapp.data.model.response

/**
 * @author ll4
 * @date 12/6/2020
 */
data class MovieResponse (
    val page: Int,
    val results: MutableList<Movie>
)