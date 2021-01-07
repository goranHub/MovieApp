package com.sicoapp.movieapp.ui.movie.popular

import androidx.lifecycle.*
import com.sicoapp.movieapp.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class PopularViewModel(
    private val remoteRepository: RemoteRepository,
    val postId: (Int) -> Unit,
    val crewID: (Int) -> Unit
) : ViewModel() {


    val adapter =
        Adapter(
            { it -> postId(it) },
            { it -> crewID(it) })

    var pageId = 1

    fun popularMovies() = liveData {
        emitSource(remoteRepository.fetchPopular(pageId).asLiveData())
        pageId++
    }
}


