package com.sicoapp.movieapp.ui.movie.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.databinding.FragmentMoviePopularBinding
import com.sicoapp.movieapp.ui.movie.search.SearchViewModel
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/1/2021
 */
@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private lateinit var binding: FragmentMoviePopularBinding

    @Inject
    lateinit var repository: RemoteRepository

    private lateinit var viewModel: PopularViewModel

    private lateinit var viewModelFactory: ViewModelFactory


    val adapter = Adapter( { postID ->
        val bundleItemId = bundleOf(ITEM_ID to postID)
        findNavController().navigate(
            R.id.action_popularMovieFragment_to_movieDetailsFragment, bundleItemId
        )

    },
        { crewID ->
            val bundleCrewId = bundleOf(CREW_ID to crewID)
            findNavController().navigate(
                R.id.action_popularMovieFragment_to_crewMovieFragment, bundleCrewId
            )

        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PopularViewModel::class.java)

        binding = FragmentMoviePopularBinding.inflate(inflater)

        binding.data = this

        observePopular()

        scrollRecyclerView()

        return binding.root
    }

    private fun observePopular() {
        viewModel.popularMovies().observe(
            viewLifecycleOwner, Observer {

                val movieResponse = it.getOrNull()

                if (movieResponse != null) {

                    val movieItemsList = movieResponse.results.map { BindMovie(it) }
                    adapter.addMovies(movieItemsList)
                }
            }
        )
    }

    private fun scrollRecyclerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    //load popular movies
                    viewModel.popularMovies().observe(
                        viewLifecycleOwner, {
                            var movieResponse = it.getOrNull()

                            if (movieResponse != null) {

                                val movieItemsList = movieResponse.results.map { BindMovie(it) }
                                adapter.addMovies(movieItemsList)
                            }
                        }
                    )
                }
            }
        })
    }
}