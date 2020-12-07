package com.sicoapp.movieapp.ui.currentItem

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @author ll4
 * @date 12/7/2020
 */

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}

/*
  context?.let {
        Glide.with(it)
            .load("https://image.tmdb.org/t/p/w185/" + poster_path)
            .into(imageView)
    }
 */