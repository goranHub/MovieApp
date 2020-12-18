package com.sicoapp.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hsalf.smileyrating.SmileyRating


/**
 * @author ll4
 * @date 12/15/2020
 */
@Entity(tableName = "MovieRating")
class MovieRatingTabelModel(
    @ColumnInfo(name = "itemId")
    var itemId: Int?,
    @ColumnInfo(name = "rating")
    var rating: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}