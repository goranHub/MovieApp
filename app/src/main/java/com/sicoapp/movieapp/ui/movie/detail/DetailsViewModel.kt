package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.movie.Movie
import com.sicoapp.movieapp.data.model.tvShow.TvResponse
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers


/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel
    (
    val api: MovieApiService,
    val itemId: Int,
    val mediaTyp: String,
    private val SmileyRepository: SmileyRepository
) : ViewModel() {

    fun insertData(itemID: Int, rating: Int) {
        SmileyRepository.insertData(itemID, rating)
    }

    fun getSavedSmileyDetails(
        itemId: Int
    ): LiveData<SmileyRatingTableModel> {
        return SmileyRepository.getMovieRatingDetails(itemId)
    }

    fun removeDataForThatItem(itemId: Int) {
        SmileyRepository.removeDataForThatItem(itemId)
    }

    fun lifeDataMovie() : LiveData<Movie> {
        val source = LiveDataReactiveStreams.fromPublisher(
            api.getByMovieID(itemId, API_KEY)
                .subscribeOn(Schedulers.io())
        )
        return source
    }

    fun lifeDataTv() : LiveData<TvResponse> {

        val sourceTv = LiveDataReactiveStreams.fromPublisher(
            api.getByTvID(itemId, API_KEY)
                .subscribeOn(Schedulers.io())
        )
        return sourceTv
    }

}

