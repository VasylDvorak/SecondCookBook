package com.example.cookbook.domain.repository.retrofit

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.domain.cache.CategoriesCache
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.repository.ICategoriesRepo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitCategoriesRepo(
    val api: IDataSource, val networkStatus: INetworkStatus
) : ICategoriesRepo {
@Inject
lateinit var categoriesCache: CategoriesCache

    override fun getCategories() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        App.instance.appComponent.inject(this)
        if (isOnline) {

            categoriesCache.newData(api)

        } else {
            categoriesCache.fromDataBaseData()
        }
    }.subscribeOn(Schedulers.io())
}
