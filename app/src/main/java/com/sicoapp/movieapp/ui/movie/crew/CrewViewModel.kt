package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CrewViewModel  @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    val adapter = CrewAdapter()

    fun loadRemoteDataCrew(movieId :Long) {
        remoteRepository
            .fetchCrew(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<Movie> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onSuccess(response: Movie) {
                        val list= response.credits.cast
                            .filter { !response.posterPath.isNullOrBlank() }
                            .distinctBy { it.profilePath }
                            .map { CrewObservable(it) }
                        adapter.addCast(list)
                    }
                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                }
            )
    }
}
