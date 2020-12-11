package com.sicoapp.movieapp.ui.movie.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieCrewBinding
import com.sicoapp.movieapp.utils.ITEM_ID


class CrewMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieCrewBinding
    var itemId = 0
    private val viewModel by lazy { CrewViewModel(itemId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            itemId = it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_crew, container, false)
        binding.data = viewModel
        return binding.root
    }
}