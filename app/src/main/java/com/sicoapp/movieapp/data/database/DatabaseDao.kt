package com.sicoapp.movieapp.data.database

import androidx.room.*
import com.sicoapp.movieapp.data.remote.firebase.model.User
import io.reactivex.Single

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSmiley(smileyRatingTableModel: SmileyRatingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    //old
    @Query("SELECT * FROM MovieRating")
    fun getSavedSmiley(): Single<List<SmileyRatingEntity>>

    @Query("SELECT * FROM UserFromFirebase")
    fun getAuthUserDB(): Single<List<User>>

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun getSmileyByMovieId(itemId: Int?): Single<SmileyRatingEntity>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun deleteSmileyByMovieId(itemId: Int)

    @Transaction
    @Query("SELECT * FROM UserFromFirebase WHERE id = :id")
    suspend fun getRatingsOfUser(id: String): List<UserWithRatings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserMovieRatingCrossRef(crossRef: UserRatingCrossRef)

}