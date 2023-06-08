package com.example.cookbook.domain.repository.retrofit

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.domain.cache.RecipeCache
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.entity.entity_menu.Menu
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitRecipeRepo (
    val api: IDataSource, val networkStatus: INetworkStatus
) : IRecipeRepo {
    @Inject
    lateinit var recipeCache: RecipeCache

    override fun getRecipes(currentItemMenu: Menu) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            App.instance.appComponent.inject(this)
            if (isOnline) {
                recipeCache.newData(currentItemMenu, api)
            } else {
                recipeCache.fromDataBaseData(currentItemMenu)
            }
        }.subscribeOn(Schedulers.io())


}