package com.sicoapp.movieapp.ui.profil

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.User
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.data.remote.response.user.UserModel
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.utils.mapToUserEntity
import com.sicoapp.movieapp.utils.mapToUserModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/22/2021
 */


class MyProfileViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {


    var bindMyProfile = BindMyProfile()

    var userModel = MutableLiveData<UserModel?>()
    private val currentUserID = FireStoreClass().currentUserID()
    private lateinit var headerProfilImageView: View
    var statusProfileUpdateSuccess = MutableLiveData<Boolean?>()
    var hideProgressDialog = MutableLiveData<Boolean?>()

    fun loadFromRemote(userFirebase: UserFirebase) {

        val user = userFirebase.mapToUserEntity()
        userModel.value = userFirebase.mapToUserModel()

        //insert into DB
        repository.insertUser(user)
        //get from DB
        getSavedUserFromDb(currentUserID)

    }

    fun profileUpdateSuccess() {
        statusProfileUpdateSuccess.value = true
    }


    fun hideProgressDialogFaliure() {
        hideProgressDialog.value = null
    }

    fun getSavedUserFromDb(currentUserID: String) {
        repository
            .getAuthUserDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<List<User>> {
                    
                    override fun onSubscribe(d: Disposable) {
                    }
                    
                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }

                    override fun onNext(response: List<User>) {
                        response.map {
                            if (it.id == currentUserID) {
                                
                                bindMyProfile.image = it.image.toString()
                                bindMyProfile.name = it.name.toString()
                                bindMyProfile.email = it.email.toString()

                            }
                        }
                    }

                    override fun onComplete() {
                    }
                }
            )
    }
}

