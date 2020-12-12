package com.sicoapp.movieapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieListBinding
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID


class ListMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel by lazy {
        //dolazi id iz ListMovieViewModel stavljamo ga u bundle i pokrecemo detailsFragment
        ListMovieViewModel({ postID ->
            val bundle = bundleOf(ITEM_ID to postID)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailsFragment,
                bundle
            )
        }, { crewID ->
            val bundleCrew = bundleOf(CREW_ID to crewID)
            findNavController().navigate(
                R.id.action_movieListFragment_to_crewMovieFragment,
                bundleCrew
            )
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.data = viewModel
        return binding.root
    }
}