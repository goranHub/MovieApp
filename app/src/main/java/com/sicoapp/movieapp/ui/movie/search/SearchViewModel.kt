package com.sicoapp.movieapp.ui.movie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.response.MovieResponse
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BindMovie
import com.sicoapp.movieapp.utils.Resource
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel(val api: MovieApiService,  val postId: (Int) -> Unit) : ViewModel() {

    val adapter = SearchAdapter { it -> postId(it) }

    fun rxToLiveData(query :String) : LiveData<MovieResponse> {
        val source = LiveDataReactiveStreams.fromPublisher(
            api.searchMovie(API_KEY, query,1 )
                .subscribeOn(Schedulers.io())
        )
        return source
    }
}