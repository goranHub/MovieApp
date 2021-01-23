package com.sicoapp.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author ll4
 * @date 12/15/2020
 */
@Entity(tableName = "MovieRating")
class SmileyRatingEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "itemId")
    var itemId: Int?,
    @ColumnInfo(name = "rating")
    var rating: Int
)