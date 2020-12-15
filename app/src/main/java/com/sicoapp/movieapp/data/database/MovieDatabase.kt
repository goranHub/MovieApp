package com.sicoapp.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel


/**
 * @author ll4
 * @date 12/15/2020
 */
@Database(entities = arrayOf(MovieRatingTabelModel::class), version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : DAOAccess

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataClient(context: Context): MovieDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }

        }
    }
}