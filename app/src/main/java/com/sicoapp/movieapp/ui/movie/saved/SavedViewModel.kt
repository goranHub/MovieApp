package com.sicoapp.movieapp.ui.movie.saved

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */
class SavedViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    val adapter = SavedAdapter()

    var allElement = mutableListOf<Movie>()

    fun loadRemoteData(valuesList: List<SmileyRatingTableModel>) {

        for (element in valuesList){

            val singleMovie =
                element.itemId?.let { remoteRepository.api.getByMovieID(it.toLong(), API_KEY) }

            singleMovie?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    object : SingleObserver<Movie> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onSuccess(response: Movie) {
                           allElement.add(response)
                           adapter.addMovieWithRating(allElement, valuesList)
                        }

                        override fun onError(e: Throwable) {
                            Log.d("error", "${e.stackTrace}")
                        }
                    }
                )
        }
    }
}


