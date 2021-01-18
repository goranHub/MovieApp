package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentIntroBinding
import com.sicoapp.movieapp.ui.movie.EntryActivity

class IntroFragment : Fragment() {

    lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentIntroBinding.inflate(inflater)

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_introFragment_to_signInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_introFragment_to_signUpFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as EntryActivity?)!!.supportActionBar?.hide()
        (activity as EntryActivity?)!!.bottomNav.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        (activity as EntryActivity?)!!.supportActionBar?.show()
        (activity as EntryActivity?)!!.bottomNav.visibility = View.VISIBLE
    }
}