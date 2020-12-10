package com.sicoapp.movieapp.ui.movie.list.newadapter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.response.topRated.Movie
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/10/2020
 */
class MovieItemViewModel(val movie: Movie) : BaseObservable() {

    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    init {
        imageUrl = "https://image.tmdb.org/t/p/w185/" + movie.poster_path
    }
}