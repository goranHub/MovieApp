package com.sicoapp.movieapp.data.response.topRated

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class TopRated(
    var id: Int,
    var original_title: String,
    var overview: String,
    var poster_path: String

    ): BaseObservable() {
    val _id: Int
        get() = id

    var title: String
        @Bindable get() = original_title
        set(value) {
            original_title = value
            notifyPropertyChanged(BR.title)
        }

    var url: String
        @Bindable get() = poster_path
        set(value) {
            poster_path = value
            notifyPropertyChanged(BR.url)
        }
}