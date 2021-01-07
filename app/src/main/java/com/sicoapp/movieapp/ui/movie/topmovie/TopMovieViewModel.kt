package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.lifecycle.*
import com.sicoapp.movieapp.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel(
    private val remoteRepository: RemoteRepository

) : ViewModel() {

    val adapter =
        Adapter(
            { it -> (it) },
            { it -> (it) })


    fun topMovies(pageId :Int) = liveData {
        emitSource(remoteRepository.fetchTopMovies(pageId).asLiveData())
    }
}


