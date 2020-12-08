package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
//databinding K u xml
//ubaciti jos u currentfrag
//pposloziti data layer
//movie od kuma
//sortiranje button listu

class DetailsMovieFragment : Fragment() {

    private lateinit var binging: FragmentMovieDetailsBinding
    var itemId = 0


    val viewModel by lazy { DetailsViewModel(itemId) }

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
        binging = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binging.data = viewModel

        return binging.root
    }
}