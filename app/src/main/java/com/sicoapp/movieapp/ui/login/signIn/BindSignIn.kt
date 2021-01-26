package com.sicoapp.movieapp.ui.login.signIn

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
class BindSignIn() : BaseObservable() {

    @get:Bindable
    var emailSignIn :String? by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.emailSignIn) }

    @get:Bindable
    var passwordSignIn :String? by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.passwordSignIn) }

}