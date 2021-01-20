package com.sicoapp.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * @author ll4
 * @date 12/15/2020
 */
@Database(entities = [SmileyRatingEntity::class], version = 1, exportSchema = false)
abstract class SmileyDatabase : RoomDatabase() {
    abstract fun movieDao() : SmileyDao
}