package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SmileyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(smileyRatingTableModel: SmileyRatingEntity)

    @Query("SELECT * FROM MovieRating")
    suspend fun getSaved(): List<SmileyRatingEntity>

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun loadById(itemId: Int?): LiveData<SmileyRatingEntity>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun deleteByID(itemId: Int)

}