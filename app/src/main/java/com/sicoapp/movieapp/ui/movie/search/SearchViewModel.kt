package com.sicoapp.movieapp.ui.movie.search

import androidx.lifecycle.*
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.data.model.movie.MovieResponse
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel( val api: MovieApiService, val postIdAndTyp: (Int, String) -> Unit) : ViewModel() {

    val adapter = SearchAdapter {
            postId,mediaTyp-> postIdAndTyp(postId,mediaTyp)
    }

    var movieLiveData = MutableLiveData<MovieResponse>()

    var serieResponse = MutableLiveData<MovieResponse>()

    var pageId = 1

    val combinedLifeData = movieLiveData.combineWith(serieResponse) {
            serieResponse, movieLiveData ->
        serieResponse
    }

    fun rxMovie(query :String) : LiveData<MovieResponse> {
        return LiveDataReactiveStreams.fromPublisher(
            api.searchMovie(API_KEY, query,1 )
                .subscribeOn(Schedulers.io())
        )
    }

    fun rxTV(query :String) : LiveData<MovieResponse> {

        return LiveDataReactiveStreams.fromPublisher(
            api.searchTvShow(API_KEY,1 , query)
                .subscribeOn(Schedulers.io())
        )
    }


    fun rxMulti(query :String) : LiveData<Multi> {

        val multi= LiveDataReactiveStreams.fromPublisher(
            api.searchMulti(API_KEY,query, pageId)
                .subscribeOn(Schedulers.io())
        )
        pageId++
        return multi
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
}