package com.sicoapp.movieapp.ui.profil

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.sicoapp.movieapp.data.database.User
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.data.remote.response.user.UserModel
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.utils.mapToUserEntity
import com.sicoapp.movieapp.utils.mapToUserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * @author ll4
 * @date 1/22/2021
 */


class MyProfileViewModel @ViewModelInject constructor(
    @ApplicationContext val appContext: Context,
    private val repository: Repository
) : ViewModel() {


    var bindMyProfile = BindMyProfile()
    var statusProfileUpdateSuccess = MutableLiveData<Boolean?>()
    var hideProgressDialogVM = MutableLiveData<Boolean?>()
    var userModel = MutableLiveData<UserModel?>()
    private val currentUserID = FireStoreClass().currentUserID()
    private var profileImageURL: String = ""
    lateinit var callback: CallbackShowProgressDialog


    interface CallbackShowProgressDialog {
        fun showProgressDialog()
    }

    interface CallbackUpdateCollection {
        fun updateCollection(profileImageURL : String)
    }

    fun loadFromRemote(userFirebase: UserFirebase) {

        val user = userFirebase.mapToUserEntity()
        userModel.value = userFirebase.mapToUserModel()

        //insert into DB
        repository.insertUser(user)
    }

    // called in FireStoreClass updateUserProfileData
    fun profileUpdateSuccess() {
        statusProfileUpdateSuccess.value = true
    }

    fun hideProgressDialogFaliure() {
        hideProgressDialogVM.value = null
    }

    fun getUserFromDbAndBind(currentUserID: String) {
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

    fun uploadImageToFireStorage(selectedImageUri: Uri?, callbackUpdateCollection : CallbackUpdateCollection) {
        if (selectedImageUri != null) {
            val storageReference =
                FirebaseStorage
                    .getInstance()
                    .reference
                    .child("USER_IMAGE" + System.currentTimeMillis() + "."
                        + fileExtension(selectedImageUri))

            storageReference
                .putFile(selectedImageUri)
                .addOnSuccessListener { snapshot ->
                    // Get the downloadable url from the snapshot
                    snapshot
                        .metadata!!
                        .reference!!
                        .downloadUrl
                        .addOnSuccessListener { uri ->
                            profileImageURL = uri.toString()
                            callbackUpdateCollection.updateCollection(profileImageURL)
                        }
                }
                .addOnFailureListener { exception ->
                }
        }
    }

    private fun fileExtension(uri: Uri?): String? {
        val cr: ContentResolver = appContext.contentResolver
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(cr.getType(uri!!))
    }


}

