package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.data.remote.firebase.model.User
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author ll4
 * @date 1/20/2021
 */
class DataBaseDataSource @Inject constructor(
    private val dao: DatabaseDao
) {

    private var smileyRatingTableModel: LiveData<SmileyRatingEntity>? = null

/*    fun insertSmiley(itemId: Int, rating: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieRatingDetails = SmileyRatingEntity(itemId, rating)
            dao.insertSmiley(movieRatingDetails)
        }
    } */


    fun insertUserMovieRatingCrossRef(itemId: Int, id: String, rating : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val userRatingCrossRef = UserRatingCrossRef(itemId, id, rating)
            dao.insertUserMovieRatingCrossRef(userRatingCrossRef)
        }
    }

   /* fun getSavedSmileys(): Single<List<SmileyRatingEntity>> {
        return dao.getSavedSmiley()
    } */

    suspend fun getRatingsOfUser():  List<UserWithRatings> {
        return dao.getRatingsOfUser(FireStoreClass().currentUserID())
    }

    fun insertUser(
        user: User
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetails = UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                image = user.image,
                movieId = user.movieId,
                movieRating = user.movieRating,
                fcmToken = user.fcmToken,
                itemId = user.itemId,
                rating = user.rating
            )
            dao.insertUser(userDetails)
        }
    }

    fun getAuthUserDB(): Single<List<User>> {
        return dao.getAuthUserDB()
    }

    fun getSmileyByMovieId(itemId: Int): Single<SmileyRatingEntity> {
        return dao.getSmileyByMovieId(itemId)
    }

 /*   fun removeDataForThatItem(itemId: Int) {
        AsyncTask.execute {
            dao.deleteByID(itemId)
        }
    }
*/

      fun deleteSmileyByMovieId(itemId: Int) {
       CoroutineScope(Dispatchers.IO).launch {
           dao.deleteSmileyByMovieId(itemId)
       }
   }
}