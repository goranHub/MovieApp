package com.sicoapp.movieapp.ui.movie.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.databinding.FragmentMoviePopularBinding
import com.sicoapp.movieapp.repository.RemoteRepository
import com.sicoapp.movieapp.utils.BindMovie
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
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
    lateinit var api: ApiServiceFlowable

    @Inject
    lateinit var remoteRepository: RemoteRepository

    private val viewModel by lazy {

        PopularViewModel(
            remoteRepository,
            { postID ->
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

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePopularBinding.inflate(inflater)

        binding.data = viewModel

        observePopular()

        scrollRecylerView()

        return binding.root
    }


    private fun observePopular() {
        viewModel.popularMovies().observe(
            viewLifecycleOwner, Observer {

                val movieResponse = it.getOrNull()

                if (movieResponse != null) {

                    val movieItemsList = movieResponse.results.map { BindMovie(it) }
                    viewModel.adapter.addMovies(movieItemsList)
                }
            }
        )
    }

    private fun scrollRecylerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.  popularMovies().observe(
                        viewLifecycleOwner, {
                            var movieResponse = it.getOrNull()

                            if (movieResponse != null) {

                                val movieItemsList = movieResponse.results.map { BindMovie(it) }
                                viewModel.adapter.addMovies(movieItemsList)
                            }
                        }
                    )
                }
            }
        })
    }
}