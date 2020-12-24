package com.sicoapp.movieapp.ui.movie.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallDetail
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel
import com.sicoapp.movieapp.repository.MovieRepository


/**
 * @author ll4
 * @date 12/6/2020
 */
class DetailsViewModel(itemId: Int) : ViewModel() {

    var detailsObserver = DetailsObserver()
    var liveDataMovieRating: LiveData<MovieRatingTabelModel>? = null

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
        MovieRepository.insertData(context, itemID, rating)
    }

    fun getMovieRatingDetails(
        context: Context,
        itemId: Int
    ): LiveData<MovieRatingTabelModel>? {
        liveDataMovieRating = MovieRepository.getMovieRatingDetails(context, itemId)
        return liveDataMovieRating
    }

    fun removeDataForThatItem(context: Context, itemId: Int) {
        MovieRepository.removeDataForThatItem(context, itemId)
    }
}

