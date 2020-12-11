package com.sicoapp.movieapp.ui.movie.crew

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.response.topRated.Crew
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/10/2020
 */
class CrewItemViewModel(val crew: Crew) : BaseObservable() {

    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    init {
        imageUrl = "https://image.tmdb.org/t/p/w185/" + crew.profile_path
    }
}