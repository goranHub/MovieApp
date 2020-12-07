package com.sicoapp.movieapp.ui.topMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentTopMovieBinding
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import com.sicoapp.movieapp.utils.ITEM_ID


class TopMoviesFragment : Fragment() {

    private lateinit var binding : FragmentTopMovieBinding
    private  val viewModel by lazy {  TopMoviesViewModel(callbackFragmentViewModel) }

    private val callbackFragmentViewModel = object : CallbackFragmentViewModelAdapter {
        override fun onItemClicked(postID: Int) {}
        override fun navigateToNextScren(postID: Int) {
            val bundle = bundleOf(ITEM_ID to postID)
            findNavController().navigate(R.id.action_topMoviesFragment_to_fightClubFragment, bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_movie, container, false)
        binding.data = viewModel
        return binding.root
    }
}