package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentIntroBinding
import kotlinx.android.synthetic.main.activity_main.*

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

        (activity as AppCompatActivity?)!!.bottom_navigation?.visibility = View.GONE
        (activity as AppCompatActivity?)!!.navigation_view?.visibility = View.GONE
        return binding.root
    }

}