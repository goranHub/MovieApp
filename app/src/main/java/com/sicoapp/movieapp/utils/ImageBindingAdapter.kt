package com.sicoapp.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @author ll4
 * @date 12/7/2020
 */

 object ImageBindingAdapter {
        @JvmStatic
        @BindingAdapter("loadImageUrl")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .into(view)
        }
    }

