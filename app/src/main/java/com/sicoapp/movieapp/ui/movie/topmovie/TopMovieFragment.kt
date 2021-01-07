package com.sicoapp.movieapp.ui.movie.topmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.FragmentMovieTopBinding
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter
import com.sicoapp.movieapp.utils.BindMovie
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieTopBinding

    private var pageId = 1

    lateinit var viewModel: TopMovieViewModel

    lateinit var adapter: Adapter

    @Inject
    lateinit var viewModelFactory: TopMovieViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieTopBinding.inflate(inflater)

        setupViewModel()

        observeTopRated()

        scrollRecylerView()

        return binding.root
    }

    private fun observeTopRated() {
        viewModel.topMovies().observe(
            viewLifecycleOwner, Observer {

                val movieResponse = it.getOrNull()

                if (movieResponse != null) {

                    val movieItemsList = movieResponse.results.map { BindMovie(it) }
                    viewModel.adapter.addMovies(movieItemsList)
                }
            }
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(TopMovieViewModel::class.java)

        binding.data = viewModel
    }


    private fun scrollRecylerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.topMovies().observe(
                        viewLifecycleOwner, Observer {

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