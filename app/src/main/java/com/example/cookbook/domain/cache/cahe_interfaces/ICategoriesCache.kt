package com.example.cookbook.domain.cache.cahe_interfaces

import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.core.Single

interface ICategoriesCache {
    fun newData(api: IDataSource): Single<List<Category>>
    fun fromDataBaseData(): Single<List<Category>>
}