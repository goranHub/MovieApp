package com.sicoapp.movieapp.ui.movie.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallDetail
import com.sicoapp.movieapp.data.model.MovieRatingTableModel
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.DetailsObserver


/**
 * @author ll4
 * @date 12/6/2020
 */
class DetailsViewModel(itemId: Int) : ViewModel() {

    var detailsObserver = DetailsObserver()
    var liveDataMovieRating: LiveData<MovieRatingTableModel>? = null

    init {
        loadDetailsMovies(itemId)
    }

    private fun loadDetailsMovies(itemId: Int) {
        retrofitCallDetail(
            itemId,
            detailsObserver
        )
    }

    fun insertData(context: Context, itemID: Int, rating: Int) {
        SmileyRepository.insertData(context, itemID, rating)
    }

    fun getSavedSmileyDetails(
        context: Context,
        itemId: Int
    ): LiveData<MovieRatingTableModel>? {
        liveDataMovieRating = SmileyRepository.getMovieRatingDetails(context, itemId)
        return liveDataMovieRating
    }

    fun removeDataForThatItem(context: Context, itemId: Int) {
        SmileyRepository.removeDataForThatItem(context, itemId)
    }
}

