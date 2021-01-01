package com.sicoapp.movieapp.ui.movie.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMoviePopularBinding
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID

/**
 * @author ll4
 * @date 1/1/2021
 */
class PopularMovieFragment : Fragment()   {



    private lateinit var binding: FragmentMoviePopularBinding
    private var pageId = 1

    private val viewModel by lazy {

        PopularViewModel(
            {
                    postID ->
                val bundleItemId = bundleOf(ITEM_ID to postID)
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_movieDetailsFragment,
                    bundleItemId
                )
            },
            {
                    crewID ->
                val bundleCrewId = bundleOf(CREW_ID to crewID)
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_crewMovieFragment,
                    bundleCrewId
                )
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_movie_popular, container, false)

        binding.data = viewModel


        binding.bottomNavigationView.setOnNavigationItemReselectedListener {

            if(it.toString().equals("Top Movie")){
                findNavController().navigate(R.id.action_popularMovieFragment_to_movieListFragment)
            }

            if(it.toString().equals("Now")){
                findNavController().navigate(R.id.action_popularMovieFragment_to_nowMovieFragment)
            }

        }

        binding.recylerViewFragmentTopMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadMovies(pageId++)
                }
            }
        })
        return binding.root
    }


}