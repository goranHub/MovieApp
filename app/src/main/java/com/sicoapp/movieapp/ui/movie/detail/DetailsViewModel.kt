package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel
import com.sicoapp.movieapp.data.response.Movie
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

    fun rxToLiveData() : LiveData<Movie> {
        val source = LiveDataReactiveStreams.fromPublisher(
            api.getByID(itemId, API_KEY)
                .subscribeOn(Schedulers.io())
        )
        return source
    }
}

