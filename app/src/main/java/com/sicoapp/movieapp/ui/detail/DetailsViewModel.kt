package com.sicoapp.movieapp.ui.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.SmileyRatingEntity
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.utils.URL_IMAGE
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel @ViewModelInject constructor(
    private var repository: Repository,
) : ViewModel() {

    var bindDetails = BindDetails()

    fun insertData(itemID: Int, rating: Int) {
        //TODO without databaseDataSource
        repository.databaseDataSource.insertData(itemID, rating)
    }

    fun getSavedSmileyDetails(
        itemId: Int
    ): LiveData<SmileyRatingEntity> {
        //TODO without databaseDataSource
        return repository.databaseDataSource.getMovieRatingDetails(itemId)
    }

    fun removeRatingForMovie(itemId: Int) {
        //TODO without databaseDataSource
        repository.databaseDataSource.removeDataForThatItem(itemId)
    }

    fun loadRemoteDataMovie(movieId :Long) {
        repository
            .fetchDetailsMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Movie> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(response: Movie) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity
                        bindDetails.releaseDate = response.releaseDate
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }

                    override fun onComplete() {
                    }
                }
            )
    }

    fun loadRemoteDataTv(movieId :Long) {
        repository
            .fetchSearchMultiTv(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : Observer<TvResponse>{
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(response: TvResponse) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity.toString()
                        bindDetails.releaseDate = response.first_air_date
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

