package com.sicoapp.movieapp.ui.movie.list.newadapter

import com.sicoapp.movieapp.data.response.topRated.Movie

/**
 * @author ll4
 * @date 12/10/2020
 */
open interface CustomClickListener {
    fun cardClicked(movie: Movie?)
}