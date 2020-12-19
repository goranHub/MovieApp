package com.sicoapp.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(movieRatingTabelModel: MovieRatingTabelModel)

    @Query("SELECT * FROM MovieRating WHERE itemId =:itemId")
    fun getMovieDetails(itemId: Int?): LiveData<MovieRatingTabelModel>

    @Query("DELETE FROM MovieRating  WHERE itemId =:itemId")
    suspend fun removeDataForThatItem(itemId: Int)

}