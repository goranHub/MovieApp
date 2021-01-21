package com.sicoapp.movieapp.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.FragmentEntryBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.utils.USER_ID

/**
 * @author ll4
 * @date 1/15/2021
 */
class SplashFragment : BaseFragment() {

    lateinit var binding: FragmentEntryBinding
    var userId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        arguments?.getString(USER_ID, "")?.let {
            userId = it
        }

        binding = FragmentEntryBinding.inflate(inflater)

        Handler().postDelayed({

            val currentUserID = FireStoreClass().currentUserID()

            if (currentUserID == userId) {
                findNavController().navigate(R.id.action_splashFragment_to_introFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.topMovieFragment,
                            true).build())

            } else {
                findNavController().navigate(R.id.action_splashFragment_to_topMovieFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.topMovieFragment,
                            true).build())
            }
        }, 1000)
        return binding.root
    }
}