package com.sicoapp.movieapp.ui.topmovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.databinding.FragmentMovieTopBinding
import com.sicoapp.movieapp.databinding.ItemMovieTopBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.ui.popular.BindMovie
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class TopMovieFragment@Inject constructor(
    val adapter : TopMovieAdapter,
    var viewModel : TopMovieViewModel? = null
)  : BaseFragment() {

    private lateinit var binding: FragmentMovieTopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(TopMovieViewModel::class.java)
        binding = FragmentMovieTopBinding.inflate(inflater)
        binding.recylerViewFragmentTopMovie.adapter = adapter
        subscribeToObservers()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@TopMovieFragment.viewModel
        }

        adapter.listenerCall = object : TopMovieAdapter.ListenerCall{
            override fun callback(binding: ItemMovieTopBinding) {
                binding.apply {
                    topMovieFragment = this@TopMovieFragment
                }
            }
        }

        scrollRecyclerView()

        return binding.root
    }


    fun openItem(movieId :Long){
        val bundleItemId = bundleOf(ITEM_ID to movieId)
        findNavController().navigate(
            R.id.action_movieListFragment_to_movieDetailsFragment,
            bundleItemId
        )
    }

    fun openCrew(crewId :Long){
        val bundleCrewId = bundleOf(CREW_ID to crewId)
        findNavController().navigate(
            R.id.action_movieListFragment_to_crewMovieFragment,
            bundleCrewId
        )
    }

    private fun subscribeToObservers() {
        var pageId = 1L
        val popular = viewModel?.getTopRated(pageId)

        popular?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                object : Observer<MovieResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(response: MovieResponse) {
                        val movieItemsList = response.results.map { BindMovie(it) }
                        adapter.addMovies(movieItemsList)
                        pageId++
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }

                    override fun onComplete() {
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
                    subscribeToObservers()
                }
            }
        })
    }
}