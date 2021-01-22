package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sicoapp.movieapp.data.database.UserEntity
import com.sicoapp.movieapp.data.remote.firebase.model.User

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(smileyRatingTableModel: SmileyRatingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM MovieRating")
    suspend fun getSaved(): List<SmileyRatingEntity>

    @Query("SELECT * FROM UserFromFirebase")
    suspend fun getSavedUser(): List<User>

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun loadById(itemId: Int?): LiveData<SmileyRatingEntity>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun deleteByID(itemId: Int)

}