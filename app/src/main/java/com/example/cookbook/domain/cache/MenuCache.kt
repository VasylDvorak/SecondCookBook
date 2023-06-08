package com.example.cookbook.domain.cache

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.data.room.Database
import com.example.cookbook.data.room.RoomMenu
import com.example.cookbook.domain.cache.cahe_interfaces.IMenuCache
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.entity.entity_menu.Menu
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MenuCache : IMenuCache {
    @Inject
    lateinit var database: Database

    init {
        App.instance.appComponent.inject(this)
    }

    override fun newData(category: Category, api: IDataSource): Single<List<Menu>> {
        return category.strCategory?.let { url ->
            api.getMenu(url)
                .flatMap { listMenu ->
                    Single.fromCallable {
                        val roomCategory = category.strCategory.let {
                            database.categoriesDao.findByCategory(it)
                        }
                        val meals = listMenu.meals
                        val roomMenu = meals.map {
                            RoomMenu(
                                it.idMeal, it.strMeal ?: "", it.strMealThumb ?: "",
                                roomCategory.idCategory
                            )
                        }
                        database.menuDao.insert(roomMenu)
                        val forSavePicture =
                            meals.map { Category(it.idMeal, it.strMeal, it.strMealThumb) }
                        Thread { PictureCache().newData(forSavePicture) }.start()

                        meals

                    }
                }
        }
            ?: Single.error<List<Menu>>(RuntimeException("Category has no menu url"))
                .subscribeOn(Schedulers.io())
    }

    override fun fromDataBaseData(category: Category): Single<List<Menu>> {
        return Single.fromCallable {
            val roomCategory =
                category.strCategory.let { database.categoriesDao.findByCategory(it) }
            database.menuDao.findForCategory(roomCategory.idCategory).map {
                Menu(it.idMeal, it.strMeal, it.strMealThumb)
            }
        }
    }
}