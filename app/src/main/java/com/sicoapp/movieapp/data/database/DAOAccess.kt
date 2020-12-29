package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(smileyRatingTableModel: SmileyRatingTableModel)

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun loadById(itemId: Int?): LiveData<SmileyRatingTableModel>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun deleteByID(itemId: Int)

}