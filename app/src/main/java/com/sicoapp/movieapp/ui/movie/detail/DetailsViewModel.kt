package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.IMovie
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.model.response.tvShow.TvResponse
import com.sicoapp.movieapp.data.repository.SmileyRepository
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers
/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel(
    val api: ApiServiceFlowable,
    private val itemId: Int,
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
        return LiveDataReactiveStreams.fromPublisher(
            api.getByMovieID(itemId, API_KEY)
                .subscribeOn(Schedulers.io())
        )
    }

    fun lifeDataTv() : LiveData<TvResponse> {
        return LiveDataReactiveStreams.fromPublisher(
            api.getByTvID(itemId, API_KEY)
                .subscribeOn(Schedulers.io())
        )
    }
}

