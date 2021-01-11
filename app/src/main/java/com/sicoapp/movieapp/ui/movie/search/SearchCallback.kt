package com.sicoapp.movieapp.ui.movie.search

/**
 * @author ll4
 * @date 1/11/2021
 */
interface SearchCallback {
    fun openDetails(movieId: Long, mediaTyp: String)
}