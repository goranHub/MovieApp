package com.sicoapp.movieapp.utils

import com.sicoapp.movieapp.data.api.MovieApiService


/**
 * @author ll4
 * @date 12/23/2020
 */
object Injection {
    fun provideMovieApiService(): MovieApiService.Companion {
        return MovieApiService.Companion
    }
}