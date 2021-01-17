package com.sicoapp.movieapp.ui.movie.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.search.adapter.SearchAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    val adapter = SearchAdapter()

    var currentPageId = 1L

    fun clear() {
        currentPageId = 1
        adapter.clearItems()
    }

    fun loadRemoteData(query: String) {
        loadData(query, 1)
    }

    fun loadMoreRemoteData(query: String) {
        loadData(query, currentPageId)
    }

    private fun loadData(query: String, pageId: Long) {
        remoteRepository
            .fetchSearchMultiMovie(query, pageId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<Multi> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(response: Multi) {
                        // var movieResponse=    response.results.filter { it.poster_path.isNullOrBlank()}
                        val movieResponse = response.results
                        val movieItemsList = movieResponse.map { BindMulti(it) }
                        adapter.updateItems(movieItemsList)
                        currentPageId++
                    }

                    override fun onError(e: Throwable) {
                    }
                }
            )
    }
}
