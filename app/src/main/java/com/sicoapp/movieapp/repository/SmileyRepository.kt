package com.sicoapp.movieapp.repository

import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.database.DAOAccess
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * @author ll4
 * @date 12/15/2020
 */

class SmileyRepository (
    val dao: DAOAccess) {

        private var smileyRatingTableModel: LiveData<SmileyRatingTableModel>? = null

        fun insertData(itemId: Int, rating: Int) {
            CoroutineScope(IO).launch {
                val movieRatingDetails = SmileyRatingTableModel(itemId, rating)
                dao.insert(movieRatingDetails)
            }
        }

        fun getMovieRatingDetails(itemId: Int): LiveData<SmileyRatingTableModel> {
            smileyRatingTableModel = dao.loadById(itemId)
            return smileyRatingTableModel as LiveData<SmileyRatingTableModel>
        }

        fun removeDataForThatItem(itemId: Int) {
            CoroutineScope(IO).launch {
                dao.deleteByID(itemId)
            }
        }

}