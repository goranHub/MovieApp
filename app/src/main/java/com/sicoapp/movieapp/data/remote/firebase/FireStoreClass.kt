package com.sicoapp.movieapp.data.remote.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sicoapp.movieapp.data.remote.firebase.model.User
import com.sicoapp.movieapp.ui.login.MyProfileFragment
import com.sicoapp.movieapp.ui.login.SignInFragment
import com.sicoapp.movieapp.ui.login.SignUpFragment
import com.sicoapp.movieapp.utils.USERS


class FireStoreClass {

    private val fireBase = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpFragment, userInfo: User) {
        fireBase.collection(USERS)
            .document(currentUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(activity.javaClass.simpleName, "Error writing document", e)
            }
    }

    fun signInUser(fragment: SignInFragment) {
        fireBase.collection(USERS)
            .document(currentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(
                    fragment.javaClass.simpleName, document.toString()
                )
                val loggedInUser = document.toObject(User::class.java)!!
                fragment.signInSuccess(loggedInUser)
            }
            .addOnFailureListener { e ->
                Log.e(fragment.javaClass.simpleName, "Error while getting loggedIn user details", e)
            }
    }

    fun currentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }


    fun loadUserData(myProfileFragment: MyProfileFragment) {
        fireBase.collection(USERS)
            .document(currentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.e(myProfileFragment.javaClass.simpleName, document.toString())
                val loggedInUser = document.toObject(User::class.java)!!
                myProfileFragment.setUserDataInUI(loggedInUser)
                }

            .addOnFailureListener { msg ->
                myProfileFragment.hideProgressDialog()
                Log.e(
                    myProfileFragment.javaClass.simpleName,
                    "Error while fetching user from cloud",msg)
            }
    }

    fun updateUserProfileData(myProfileFragment: MyProfileFragment, userHashMap: HashMap<String, Any>) {
        fireBase.collection(USERS)
            .document(currentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                Log.e(myProfileFragment.javaClass.simpleName, "updated was successfully")
                myProfileFragment.profileUpdateSuccess()
            }
            .addOnFailureListener { msg ->

                myProfileFragment.hideProgressDialog()
                Log.e(
                    myProfileFragment.javaClass.simpleName,
                    "Error while updating user",msg)
            }
    }
}