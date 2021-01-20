package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/20/2021
 */
class DataBaseDataSource @Inject constructor(
    private val smileyDao: SmileyDao
){

    private var smileyRatingTableModel: LiveData<SmileyRatingEntity>? = null

    fun insertData(itemId: Int, rating: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieRatingDetails = SmileyRatingEntity(itemId, rating)
            smileyDao.insert(movieRatingDetails)
        }
    }

    suspend fun getSaved(): List<SmileyRatingEntity> {
        return smileyDao.getSaved()
    }

    fun getMovieRatingDetails(itemId: Int): LiveData<SmileyRatingEntity> {
        smileyRatingTableModel = smileyDao.loadById(itemId)
        return smileyRatingTableModel as LiveData<SmileyRatingEntity>
    }

    fun removeDataForThatItem(itemId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            smileyDao.deleteByID(itemId)
        }
    }

}