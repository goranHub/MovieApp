package com.sicoapp.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sicoapp.movieapp.data.model.MovieRatingTableModel


/**
 * @author ll4
 * @date 12/15/2020
 */
@Database(entities = [MovieRatingTableModel::class], version = 1, exportSchema = false)
abstract class MovieDatabaseForSmiley : RoomDatabase() {

    abstract fun movieDao() : DAOAccess

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabaseForSmiley? = null

        fun getDataClient(context: Context): MovieDatabaseForSmiley {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabaseForSmiley::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}