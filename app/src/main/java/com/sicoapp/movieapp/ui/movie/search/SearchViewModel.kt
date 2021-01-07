package com.sicoapp.movieapp.ui.movie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel(val remoteRepository: RemoteRepository) :
    ViewModel() {

    var pageId = 1

    fun rxMulti(query: String): LiveData<Multi> {
        val multi = LiveDataReactiveStreams.fromPublisher(
            remoteRepository.apis.searchMulti(API_KEY, query, pageId)
                .subscribeOn(Schedulers.io())
        )
        pageId++
        return multi
    }
}
/*
    var movieLiveData = MutableLiveData<MovieResponse>()
    var serieResponse = MutableLiveData<MovieResponse>()

    val combinedLifeData =
        movieLiveData.combineWith(serieResponse) { serieResponse, movieLiveData ->
            serieResponse
        }

    fun rxMovie(query: String): LiveData<MovieResponse> {
        return LiveDataReactiveStreams.fromPublisher(
            api.searchMovie(API_KEY, query, 1)
                .subscribeOn(Schedulers.io())
        )
    }

    fun rxTV(query: String): LiveData<MovieResponse> {

        return LiveDataReactiveStreams.fromPublisher(
            api.searchTvShow(API_KEY, 1, query)
                .subscribeOn(Schedulers.io())
        )
    }

    private fun <T, K, R> LiveData<T>.combineWith(
        liveData: LiveData<K>,
        block: (T?, K?) -> R
    ): LiveData<R> {
        val result = MediatorLiveData<R>()
        result.addSource(this) {
            result.value = block(this.value, liveData.value)
        }
        result.addSource(liveData) {
            result.value = block(this.value, liveData.value)
        }
        return result
    }
 */
