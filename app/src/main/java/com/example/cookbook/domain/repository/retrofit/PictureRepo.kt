package com.example.cookbook.domain.repository.retrofit

import com.example.cookbook.application.App
import com.example.cookbook.data.room.RoomPicture
import com.example.cookbook.domain.cache.PictureCache
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.repository.IPictureRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PictureRepo : IPictureRepo {

    @Inject
    lateinit var pictureCache: PictureCache

    override fun getPicturesDataForDTO(inputCategories: List<Category>): Single<List<RoomPicture>> {

        App.instance.appComponent.inject(this)
        return pictureCache.newData(inputCategories)
            .subscribeOn(Schedulers.io())

    }
}