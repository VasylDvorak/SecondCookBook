package com.example.cookbook.di.di_categories.module


import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.di.di_categories.CategoriesScope
import com.example.cookbook.di.di_categories.ICategoriesScopeContainer
import com.example.cookbook.domain.cache.CategoriesCache
import com.example.cookbook.domain.cache.cahe_interfaces.ICategoriesCache
import com.example.cookbook.domain.repository.ICategoriesRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitCategoriesRepo
import dagger.Module
import dagger.Provides

@Module
open class CategoriesModule {
    @Provides
    fun categoriesCache(): ICategoriesCache {
        return CategoriesCache()
    }

    @CategoriesScope
    @Provides
    open fun categoriesRepo(api: IDataSource, networkStatus: INetworkStatus): ICategoriesRepo {
        return RetrofitCategoriesRepo(api, networkStatus)
    }

    @CategoriesScope
    @Provides
    open fun scopeContainer(app: App): ICategoriesScopeContainer = app
}