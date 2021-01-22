package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.remote.firebase.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/20/2021
 */
class DataBaseDataSource @Inject constructor(
    private val dao: Dao
) {

    private var smileyRatingTableModel: LiveData<SmileyRatingEntity>? = null

    fun insertData(itemId: Int, rating: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieRatingDetails = SmileyRatingEntity(itemId, rating)
            dao.insert(movieRatingDetails)
        }
    }

    suspend fun getSavedSmileys(): List<SmileyRatingEntity> {
        return dao.getSaved()
    }

    fun insertDataUser(
     user : User
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetails = UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                image = user.image,
                movieId = user.movieId,
                movieRating = user.movieRating,
                fcmToken = user.fcmToken)
            dao.insertUser(userDetails)
        }
    }

    suspend fun getSavedUsers():  List<User> {
        return dao.getSavedUser()
    }

    fun getMovieRatingDetails(itemId: Int): LiveData<SmileyRatingEntity> {
        smileyRatingTableModel = dao.loadById(itemId)
        return smileyRatingTableModel as LiveData<SmileyRatingEntity>
    }

    fun removeDataForThatItem(itemId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteByID(itemId)
        }
    }
}