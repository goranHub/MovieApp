package com.sicoapp.movieapp.data.model.response.multi

data class Multi(
    val page: Int,
    val results: List<MultiResult>,
    val total_pages: Int,
    val total_results: Int
)