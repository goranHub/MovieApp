package com.sicoapp.movieapp.data.database

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Junction



/**
 * @author ll4
 * @date 1/23/2021
 */


data class UserWithRatings(

    @Embedded val user: UserEntity,

    @Relation(

        parentColumn = "id",
        entityColumn = "itemId",
        associateBy = Junction(UserRatingCrossRef::class)

    )

    val rating: List<SmileyRatingEntity>

)