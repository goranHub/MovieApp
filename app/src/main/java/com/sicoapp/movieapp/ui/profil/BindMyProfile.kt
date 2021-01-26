package com.sicoapp.movieapp.ui.profil

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.remote.response.profil.Profil
import com.sicoapp.movieapp.utils.URL_IMAGE
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/10/2020
 */
class BindMyProfile() : BaseObservable() {

    @get:Bindable
    var image :String? by Delegates.observable("TEST image") { _, _, _ -> notifyPropertyChanged(BR.image) }

    @get:Bindable
    var name :String? by Delegates.observable("TEST name") { _, _, _ -> notifyPropertyChanged(BR.name) }

    @get:Bindable
    var email :String? by Delegates.observable("TEST email") { _, _, _ -> notifyPropertyChanged(BR.email) }


}