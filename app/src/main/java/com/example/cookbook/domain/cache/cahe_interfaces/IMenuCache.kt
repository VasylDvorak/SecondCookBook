package com.example.cookbook.domain.cache.cahe_interfaces

import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.core.Single

interface IMenuCache {
    fun newData(category: Category, api: IDataSource): Single<List<Menu>>
    fun fromDataBaseData(user: Category): Single<List<Menu>>
}