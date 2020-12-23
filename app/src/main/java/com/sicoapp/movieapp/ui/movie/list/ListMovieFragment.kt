package com.sicoapp.movieapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentMovieListBinding
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID


class ListMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private var pageId = 1

    private val viewModel by lazy {

        ListMovieViewModel({ postID ->
            val bundleItemId = bundleOf(ITEM_ID to postID)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailsFragment,
                bundleItemId
            )
        }, { crewID ->
            val bundleCrewId = bundleOf(CREW_ID to crewID)
            findNavController().navigate(
                R.id.action_movieListFragment_to_crewMovieFragment,
                bundleCrewId
            )
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // add dividers between RecyclerView's row items

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.data = viewModel


        binding.topAppBar.setNavigationOnClickListener {
            val text = "more"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }


        binding.recylerViewFragmentTopMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadMovies(pageId++)
                }
                if (!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadMovies(pageId--)
                }
            }
        })
        return binding.root
    }
}