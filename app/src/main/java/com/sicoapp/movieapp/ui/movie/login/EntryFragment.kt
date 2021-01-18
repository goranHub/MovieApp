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
import com.sicoapp.movieapp.ui.movie.BaseFragment
import com.sicoapp.movieapp.utils.USER_ID
import kotlinx.android.synthetic.main.activity_entry.*

/**
 * @author ll4
 * @date 1/15/2021
 */
class EntryFragment : BaseFragment() {

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

            val currentUserID = FireStoreClass().getCurrentUserID()

            if (currentUserID == userId) {
                findNavController().navigate(R.id.action_entryFragment_to_introFragment)
            } else {
                findNavController().navigate(R.id.action_entryFragment_to_topMovieFragment)
            }
        }, 1000)

        bottomNavigationView?.visibility = View.GONE
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