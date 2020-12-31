package com.sicoapp.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sicoapp.movieapp.data.model.SmileyRatingTableModel


/**
 * @author ll4
 * @date 12/15/2020
 */
@Database(entities = [SmileyRatingTableModel::class], version = 1, exportSchema = false)
abstract class SmileyDatabase : RoomDatabase() {
    abstract fun movieDao() : DAOAccess
}