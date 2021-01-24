package com.sicoapp.movieapp.data.database

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

    fun insertSmiley(itemId: Int, rating: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val rating = Rating(itemId.toString(), rating)
            dao.insertSmiley(rating)
        }
    }


    suspend fun getRatingsOfUser(curenntUSer : String): List<UserWithRatings> {
        return dao.getRatingsOfUser(curenntUSer)
    }

    fun insertUser(
        user: User
    )
    {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertUser(user)
        }
    }

    fun getAuthUserDB(): Single<List<User>> {
        return dao.getAuthUserDB()
    }

    fun getSmileyByMovieId(itemId: Int): Single<Rating> {
        return dao.getSmileyByMovieId(itemId)
    }


    fun deleteSmileyByMovieId(itemId: Int) {
       CoroutineScope(Dispatchers.IO).launch {
           dao.deleteSmileyByMovieId(itemId)
       }
    }

    suspend fun insertStudentSubjectCrossRef(crossRef : UserRatingsCrossRef){
        dao.insertStudentSubjectCrossRef(crossRef)
    }
}