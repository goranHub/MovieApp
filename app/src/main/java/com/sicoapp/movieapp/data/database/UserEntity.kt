package com.sicoapp.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author ll4
 * @date 1/22/2021
 */

@Entity(tableName = "UserFromFirebase")
data class UserEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "image")
    var image: String?,
    @ColumnInfo(name = "movieId")
    var movieId: String?,
    @ColumnInfo(name = "movieRating")
    var movieRating: String?,
    @ColumnInfo(name = "fcmToken")
    var fcmToken: String?
)