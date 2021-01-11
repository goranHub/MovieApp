package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.popular.BindMovie
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.TopMovieAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel @ViewModelInject constructor(
    private var remoteRepository: RemoteRepository
) : ViewModel() {

    var pageId = 1

    val adapter = TopMovieAdapter()

    init {
        loadRemoteData()
    }

     fun loadRemoteData() {
        remoteRepository
            .fetchTopMovies(pageId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<MovieResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(response: MovieResponse) {
                        val movieItemsList = response.results.map { BindMovie(it) }
                        adapter.addMovies(movieItemsList)
                        pageId++
                    }

                    override fun onError(e: Throwable) {
                    }

                }
            )
    }
}


