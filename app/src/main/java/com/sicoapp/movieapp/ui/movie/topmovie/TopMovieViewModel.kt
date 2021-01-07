package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.lifecycle.*
import com.sicoapp.movieapp.data.repository.RemoteRepository

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    var pageId = 1

    fun topMovies() = liveData {
        emitSource(remoteRepository.fetchTopMovies(pageId).asLiveData())
        pageId++
    }
}


