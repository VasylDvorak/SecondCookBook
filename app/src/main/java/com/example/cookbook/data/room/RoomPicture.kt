package com.example.cookbook.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomCategory::class,
        parentColumns = ["idCategory"],
        childColumns = ["idCategory"],
        onDelete = ForeignKey.NO_ACTION
    )]
)

data class RoomPicture(
    @PrimaryKey var idCategory: String,
    var strCategoryThumb: String,
    var local_path: String
)
