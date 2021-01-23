package com.sicoapp.movieapp.data.database

import androidx.room.Entity

@Entity(primaryKeys = ["itemId", "id"])
data class UserRatingCrossRef(

    val itemId: Int,
    val id: String,
    val rating: Int

)