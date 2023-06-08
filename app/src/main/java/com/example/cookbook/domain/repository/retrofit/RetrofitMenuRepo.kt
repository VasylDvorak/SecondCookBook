package com.example.cookbook.domain.repository.retrofit

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.domain.cache.MenuCache
import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitMenuRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus
) : IMenuRepo {
    @Inject
    lateinit var menuCache: MenuCache

    override fun getMenu(category: Category) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            App.instance.appComponent.inject(this)
            if (isOnline) {
                menuCache.newData(category, api)
            } else {
                menuCache.fromDataBaseData(category)
            }
        }.subscribeOn(Schedulers.io())
}
