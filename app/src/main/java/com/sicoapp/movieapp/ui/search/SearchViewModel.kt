package com.sicoapp.movieapp.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.ui.popular.BindMovie
import com.sicoapp.movieapp.ui.search.adapter.SearchAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository
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
        repository
            .fetchSearchMulti(query, pageId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Multi> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(response: Multi) {
                        val movieResponse =
                            response.results
                                .filter { !it.poster_path.isNullOrBlank() }
                                .distinctBy { it.poster_path }
                                .map { BindMulti(it) }

                        adapter.updateItems(movieResponse)
                        currentPageId++
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }

                    override fun onComplete() {
                    }
                }
            )
    }
}
