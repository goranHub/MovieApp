package com.sicoapp.movieapp.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hsalf.smileyrating.SmileyRating
import com.hsalf.smileyrating.SmileyRating.OnSmileySelectedListener
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import kotlinx.android.synthetic.main.fragment_movie_details.*

class DetailsMovieFragment : Fragment() {

    private lateinit var binging: FragmentMovieDetailsBinding
    var itemId = 0

    private val viewModel by lazy { DetailsViewModel(itemId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }
    }

    //Spremis koji je film i koji je rating i kad otvoris taj film da vidi imal za njeg rating

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binging = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        )

        binging.smiley.setSmileySelectedListener { type ->
            status.text = type.toString()
        }

        binging.data = viewModel
        return binging.root
    }
}