package com.sicoapp.movieapp.ui.movie.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @author ll4
 * @date 12/7/2020
 */

interface ImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .into(view)
        }
    }
}
