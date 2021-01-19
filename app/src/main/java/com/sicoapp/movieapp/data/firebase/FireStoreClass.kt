package com.sicoapp.movieapp.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sicoapp.movieapp.data.firebase.model.User
import com.sicoapp.movieapp.ui.movie.login.SignInFragment
import com.sicoapp.movieapp.ui.movie.login.SignUpFragment
import com.sicoapp.movieapp.utils.USERS


class FireStoreClass {

    private val fireBase = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpFragment, userInfo: User) {

        fireBase.collection(USERS)
                .document(getCurrentUserID())
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activity.userRegisteredSuccess()
                }
                .addOnFailureListener { e ->
                    Log.e(activity.javaClass.simpleName,"Error writing document",e)
                }
    }


    fun signInUser(activity: SignInFragment) {

        fireBase.collection(USERS)
                // The document id to get the Fields of user.
                .document(getCurrentUserID())
                .get()
                .addOnSuccessListener { document ->
                    Log.e(
                            activity.javaClass.simpleName, document.toString()
                    )
                    val loggedInUser = document.toObject(User::class.java)!!
                    activity.signInSuccess(loggedInUser)
                }
                .addOnFailureListener { e ->
                    Log.e(activity.javaClass.simpleName,"Error while getting loggedIn user details",e)}
    }

    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }
}