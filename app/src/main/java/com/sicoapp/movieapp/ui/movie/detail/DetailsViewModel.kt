package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallDetail
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel
import com.sicoapp.movieapp.repository.SmileyRepository
import com.sicoapp.movieapp.utils.DetailsObserver
import androidx.hilt.lifecycle.ViewModelInject


/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel  @ViewModelInject
constructor(
    itemId: Int,
    private val SmileyRepository: SmileyRepository
) : ViewModel() {

    var detailsObserver = DetailsObserver()

    init {
        loadDetailsMovies(itemId)
    }

    private fun loadDetailsMovies(itemId: Int) {
        retrofitCallDetail(
            itemId,
            detailsObserver
        )
    }

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
}

