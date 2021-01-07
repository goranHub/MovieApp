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

    val postId : (Int) -> Unit = {}
    val crewID : (Int) -> Unit = {}

    val adapter =
        Adapter(
            { it -> postId(it) },
            { it -> crewID(it) })

    var pageId = 1


    fun topMovies() = liveData {
        emitSource(remoteRepository.fetchTopMovies(pageId).asLiveData())
        pageId++
    }
}


