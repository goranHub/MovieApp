package com.sicoapp.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author ll4
 * @date 12/15/2020
 */
@Entity(tableName = "MovieRating")
class SmileyRatingTableModel(
    @ColumnInfo(name = "itemId")
    var itemId: Int?,
    @ColumnInfo(name = "rating")
    var rating: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}