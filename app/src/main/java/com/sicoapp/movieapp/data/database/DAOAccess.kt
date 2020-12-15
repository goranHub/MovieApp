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
    fun InsertData(movieRatingTabelModel: MovieRatingTabelModel)

    @Query("SELECT * FROM MovieRating WHERE OriginalTitle =:originalTitle")
    fun getMovieDetails(originalTitle: String?) : LiveData<MovieRatingTabelModel>

}