package com.example.cookbook.di.di_recipe.module


import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.di.di_recipe.IRecipeScopeContainer
import com.example.cookbook.di.di_recipe.RecipeScope
import com.example.cookbook.domain.cache.RecipeCache
import com.example.cookbook.domain.cache.cahe_interfaces.IRecipeCache
import com.example.cookbook.domain.repository.retrofit.IRecipeRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitRecipeRepo
import dagger.Module
import dagger.Provides

@Module
open class RecipeModule {
    @Provides
    fun recipeCache(): IRecipeCache {
        return RecipeCache()
    }

    @RecipeScope
    @Provides
    open fun recipeRepo(api: IDataSource, networkStatus: INetworkStatus):
            IRecipeRepo {
        return RetrofitRecipeRepo(api, networkStatus)
    }

    @RecipeScope
    @Provides
    open fun scopeContainer(app: App): IRecipeScopeContainer = app


}