package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.FragmentEntryBinding
import com.sicoapp.movieapp.databinding.FragmentIntroBinding
import com.sicoapp.movieapp.ui.movie.BaseFragment

/**
 * @author ll4
 * @date 1/15/2021
 */
class EntryFragment : BaseFragment() {


    lateinit var binding: FragmentEntryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryBinding.inflate(inflater)

        Handler().postDelayed({
            val currentUserID = FireStoreClass().getCurrentUserID()
            if (currentUserID.isNotEmpty()) {
                findNavController().navigate(R.id.action_entryFragment_to_mainActivity)
            } else {
                findNavController().navigate(R.id.action_entryFragment_to_introFragment)
            }
            activity?.finish()
        }, 1000)

        return binding.root
    }


}