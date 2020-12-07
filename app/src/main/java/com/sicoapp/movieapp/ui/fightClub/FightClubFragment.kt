package com.sicoapp.movieapp.ui.fightClub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentFightClubBinding
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import com.sicoapp.movieapp.utils.ITEM_ID


class FightClubFragment : Fragment() {


    private lateinit var binging: FragmentFightClubBinding

    private val viewModel by lazy { FightClubViewModel(callbackFragmentViewModel) }


    private val callbackFragmentViewModel = object : CallbackFragmentViewModelAdapter {
        override fun onItemClicked(postID: Int) {}
        override fun navigateToNextScren(postID: Int) {
            val bundle = bundleOf(ITEM_ID to postID)
            findNavController().navigate(R.id.action_fightClubFragment_to_topMoviesFragment, bundle)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = DataBindingUtil.inflate(inflater, R.layout.fragment_fight_club, container, false)
        binging.data = viewModel

        //val recylerView = binging.recylerViewFragmentFightClub
        //recylerView.layoutManager = LinearLayoutManager(context)
        // FightClubViewModel(callbackFragmentViewModel).retrofitCall(recylerView)
        return binging.root
    }
}