package com.sicoapp.movieapp.ui.fightClub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentFightClubBinding
import com.sicoapp.movieapp.utils.ITEM_ID


class CurrentItemFragment : Fragment() {

    private lateinit var binging: FragmentFightClubBinding
    var itemId = 0
    private val viewModel by lazy { CurrentItemViewModel(itemId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         arguments?.getInt(ITEM_ID, -1)?.let {
             itemId = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = DataBindingUtil.inflate(inflater, R.layout.fragment_fight_club, container, false)
        binging.data = viewModel
        return binging.root
    }
}