package com.sicoapp.movieapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.ui.movie.search.SearchCallback
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import com.sicoapp.movieapp.ui.movie.topmovie.TopMovieCallback
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.TopMovieAdapter

/**
 * @author ll4
 * @date 1/11/2021
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setCallback")
    fun setCallback(view: RecyclerView, callback: TopMovieCallback) {
        val adapterTop = view.adapter as? TopMovieAdapter ?: return
        adapterTop.callback = callback
    }


    @JvmStatic
    @BindingAdapter("setCallbackSearch")
    fun setCallbackSearch(view: RecyclerView, callback: SearchCallback) {
        val adapterTop = view.adapter as? SearchAdapter ?: return
        adapterTop.callback = callback
    }
}