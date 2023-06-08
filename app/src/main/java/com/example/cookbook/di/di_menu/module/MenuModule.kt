package com.example.cookbook.di.di_menu.module

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.di.di_menu.IMenuScopeContainer
import com.example.cookbook.di.di_menu.MenuScope
import com.example.cookbook.domain.cache.MenuCache
import com.example.cookbook.domain.cache.cahe_interfaces.IMenuCache
import com.example.cookbook.domain.repository.retrofit.IMenuRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitMenuRepo
import dagger.Module
import dagger.Provides

@Module
open class MenuModule {
    @Provides
    fun menuCache(): IMenuCache {
        return MenuCache()
    }

    @MenuScope
    @Provides
    open fun menuRepo(api: IDataSource, networkStatus: INetworkStatus):
            IMenuRepo {
        return RetrofitMenuRepo(api, networkStatus)
    }

    @MenuScope
    @Provides
    open fun scopeContainer(app: App): IMenuScopeContainer = app

}