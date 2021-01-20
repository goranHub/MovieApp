package com.sicoapp.movieapp.ui.topmovie

/**
 * @author ll4
 * @date 1/11/2021
 */
interface TopMovieCallback {
    fun openDetails(movieId: Long)
    fun openCrew(crewId: Long)
}