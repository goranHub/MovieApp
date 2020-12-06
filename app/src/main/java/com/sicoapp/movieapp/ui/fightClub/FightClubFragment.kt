package com.sicoapp.movieapp.ui.fightClub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import com.sicoapp.movieapp.utils.ITEM_ID


class FightClubFragment : Fragment() {


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
        val view = inflater.inflate(R.layout.fragment_fight_club, container, false)
        val recylerView = view.findViewById<RecyclerView>(R.id.recylerViewFragmentFightClub)
        recylerView.layoutManager = LinearLayoutManager(context)
        FightClubViewModel(recylerView, callbackFragmentViewModel).retrofitCall()
        return view
    }
}