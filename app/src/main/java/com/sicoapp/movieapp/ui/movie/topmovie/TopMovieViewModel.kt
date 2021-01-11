package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sicoapp.movieapp.data.repository.RemoteRepository
import javax.inject.Inject

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel @ViewModelInject constructor(
    private var remoteRepository: RemoteRepository
) : ViewModel() {

    var pageId = 1

    suspend fun topMovies() = liveData {
        emitSource(remoteRepository.fetchTopMovies(pageId).asLiveData())
        pageId++
    }
}


