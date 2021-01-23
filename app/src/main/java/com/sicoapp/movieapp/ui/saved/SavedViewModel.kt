package com.sicoapp.movieapp.ui.saved

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.UserWithRatings
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.domain.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */
class SavedViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val adapter = SavedAdapter()
    var allElement = mutableListOf<Movie>()

    /*   fun getSavedSmiley() : Single<List<SmileyRatingEntity>>{
           return repository.getSavedSmiley()
       }*/

    suspend fun getRatingsOfUser(): List<UserWithRatings> {
        return repository.getRatingsOfUser()
    }

/*    fun getByMovieID(valuesList: List<SmileyRatingEntity>) {
        for (element in valuesList) {
            val singleMovie =
                repository
                    .getMovieByID(element.itemId.let { it!!.toLong() })

            singleMovie
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : Observer<Movie> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(response: Movie) {
                            allElement.add(response)
                            adapter.addMovieWithRating(allElement, valuesList)
                        }

                        override fun onError(e: Throwable) {
                            Log.d("error", "${e.stackTrace}")
                        }

                        override fun onComplete() {
                        }
                    }
                )
        }
    }*/

    fun getByMovieID(list: List<UserWithRatings>) {

        list.map {
            var valuesList = it.rating


            for (element in valuesList) {
                val singleMovie =
                    repository
                        .getMovieByID(element.itemId.let { it!!.toLong() })

                singleMovie
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        object : Observer<Movie> {
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(response: Movie) {
                                allElement.add(response)
                                adapter.addMovieWithRating(allElement, valuesList)
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

    }
}


