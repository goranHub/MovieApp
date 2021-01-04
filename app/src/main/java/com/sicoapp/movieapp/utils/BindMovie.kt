package com.sicoapp.movieapp.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.model.response.Movie
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/10/2020
 */
class BindMovie(val movie: Movie) : BaseObservable() {

    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    init {
        imageUrl = URL_IMAGE + movie.posterPath
    }
}