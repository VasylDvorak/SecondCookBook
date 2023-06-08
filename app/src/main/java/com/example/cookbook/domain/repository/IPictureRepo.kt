package com.example.cookbook.domain.repository

import com.example.cookbook.data.room.RoomPicture
import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.core.Single

interface IPictureRepo {
    fun getPicturesDataForDTO(inputCategories: List<Category>): Single<List<RoomPicture>>
}