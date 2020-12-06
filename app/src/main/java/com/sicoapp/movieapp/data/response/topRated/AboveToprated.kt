package com.sicoapp.movieapp.data.response.topRated

/**
 * @author ll4
 * @date 12/6/2020
 */
data class AboveTopRated (
    val page: Int,
    val results: List<TopRated>,
    val totalResults: Int,
    val totalPages :Int
)