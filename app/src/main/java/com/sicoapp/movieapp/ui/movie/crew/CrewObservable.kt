package com.sicoapp.movieapp.ui.movie.crew

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.utils.URL_IMAGE
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/10/2020
 */

/*
bind the property profilePath
from response with layout

 */
class CrewObservable(val crew: Crew) : BaseObservable() {

    @get:Bindable
    var imageActorUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageActorUrl) }

    init {
        imageActorUrl = URL_IMAGE + crew.profilePath
    }
}