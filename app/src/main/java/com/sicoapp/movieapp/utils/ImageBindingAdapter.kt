package com.sicoapp.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.R


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





