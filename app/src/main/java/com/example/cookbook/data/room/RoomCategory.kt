package com.example.cookbook.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCategory(
    @PrimaryKey var idCategory: String,
    var strCategory: String,
    var strCategoryThumb: String? = null,
    var strCategoryDescription: String? = null
)