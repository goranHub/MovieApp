package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass
import com.sicoapp.movieapp.data.firebase.model.User
import com.sicoapp.movieapp.databinding.FragmentSignInBinding
import com.sicoapp.movieapp.ui.movie.BaseFragment


class SignInFragment : BaseFragment() {

    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater)

        binding.btnSignIn.setOnClickListener {
            signInRegisteredUser()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar?.subtitle = "SIGN IN"
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
    }

    private fun signInRegisteredUser() {
        val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
        val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In  FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        FireStoreClass().signInUser(this@SignInFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }

    fun signInSuccess(user: User) {
        hideProgressDialog()

        findNavController().navigate(
            R.id.action_signInFragment_to_topMovieFragment)
    }
}