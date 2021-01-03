package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel
import com.sicoapp.movieapp.repository.SmileyRepository


/**
 * @author ll4
 * @date 12/6/2020
 */

class DetailsViewModel
    (private val SmileyRepository: SmileyRepository) : ViewModel() {

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

