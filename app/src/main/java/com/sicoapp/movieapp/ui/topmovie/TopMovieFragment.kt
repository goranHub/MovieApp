package com.sicoapp.movieapp.ui.topmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieTopBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopMovieFragment : BaseFragment() {

    private val viewModel: TopMovieViewModel by viewModels()

    private lateinit var binding: FragmentMovieTopBinding


    val callback = object : TopMovieCallback {
        override fun openDetails(movieId: Long) {
            val bundleItemId = bundleOf(ITEM_ID to movieId)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailsFragment,
                bundleItemId
            )
        }

        override fun openCrew(crewId: Long) {
            val bundleCrewId = bundleOf(CREW_ID to crewId)
            findNavController().navigate(
                R.id.action_movieListFragment_to_crewMovieFragment,
                bundleCrewId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMovieTopBinding.inflate(inflater)

        viewModel.adapter.callback = callback

        binding.data = viewModel

        scrollRecyclerView()

        return binding.root
    }

    private fun scrollRecyclerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getTopRated()
                }
            }
        })
    }

}