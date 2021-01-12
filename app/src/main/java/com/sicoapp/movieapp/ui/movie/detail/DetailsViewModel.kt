package com.sicoapp.movieapp.ui.movie.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.model.response.tvShow.TvResponse
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.data.repository.SmileyRepository
import com.sicoapp.movieapp.utils.URL_IMAGE
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel @ViewModelInject constructor(
    private var remoteRepository: RemoteRepository,
    private val SmileyRepository: SmileyRepository
) : ViewModel() {

    var bindDetails = BindDetails()

    fun insertData(itemID: Int, rating: Int) {
        SmileyRepository.insertData(itemID, rating)
    }

    fun getSavedSmileyDetails(
        itemId: Int
    ): LiveData<SmileyRatingTableModel> {
        return SmileyRepository.getMovieRatingDetails(itemId)
    }

    fun removeRatingForMovie(itemId: Int) {
        SmileyRepository.removeDataForThatItem(itemId)
    }

    fun loadRemoteDataMovie(movieId :Long) {
        remoteRepository
            .fetchSearchMultiMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<Movie> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onSuccess(response: Movie) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity
                        bindDetails.releaseDate = response.releaseDate
                    }
                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                }
            )
    }

    fun loadRemoteDataTv(movieId :Long) {
        remoteRepository
            .fetchSearchMultiTv(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<TvResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onSuccess(response: TvResponse) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity.toString()
                        bindDetails.releaseDate = response.first_air_date
                    }
                    override fun onError(e: Throwable) {
                    }
                }
            )
    }
}

