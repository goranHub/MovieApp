package com.sicoapp.movieapp.ui.movie.popular

import androidx.lifecycle.*
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class PopularViewModel(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {
    var pageId = 1

    fun popularMovies() = liveData {
        emitSource(remoteRepository.fetchPopular(pageId).asLiveData())
        pageId++
    }
}


