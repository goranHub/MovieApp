package com.sicoapp.movieapp.utils

import com.sicoapp.movieapp.data.response.topRated.TopRated

/**
 * @author ll4
 * @date 12/7/2020
 */
interface CallbackList {
    fun listToFragment (lista : TopRated)
}