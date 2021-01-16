package com.sicoapp.movieapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sicoapp.movieapp.R

/**
 * @author ll4
 * @date 12/7/2020
 */

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 40f
        start()
    }
}

fun ImageView.loadImage(uri:String, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions().placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load(uri).into(this)
}

/*object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun loadImage(view:ImageView , url:String){
        view.loadImage(url, getProgressDrawable(view.context))
    }*/

    object ImageBindingAdapter {
        @JvmStatic
        @BindingAdapter("loadImageUrl")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .into(view)
        }
    }
/*}*/










