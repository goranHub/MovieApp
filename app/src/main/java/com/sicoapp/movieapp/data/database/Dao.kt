package com.sicoapp.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sicoapp.movieapp.data.remote.firebase.model.User
import io.reactivex.Single

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(smileyRatingTableModel: SmileyRatingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM MovieRating")
    fun getSaved(): Single<List<SmileyRatingEntity>>

    @Query("SELECT * FROM UserFromFirebase")
    fun getSavedUser(): Single<List<User>>

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun loadById(itemId: Int?): Single<SmileyRatingEntity>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun deleteByID(itemId: Int)

}