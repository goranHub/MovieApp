package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.movie.MovieResponse
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel(
    val api: MovieApiService,
    private val pageId: Int,
    val postId: (Int) -> Unit,
    val crewID: (Int) -> Unit
) : ViewModel() {

    // iz adaptera id stavljamo u postId od ListMovieViewModel

    val adapter =
        Adapter(
            { it -> postId(it) },
            { it -> crewID(it) })


    fun rxToLiveData() : LiveData<MovieResponse> {
        val source = LiveDataReactiveStreams.fromPublisher(
            api.loadTopRated(API_KEY, pageId.toString())
                .subscribeOn(Schedulers.io())
        )
        return source
    }
}


