package com.example.cookbook.domain.cache


import android.database.sqlite.SQLiteConstraintException
import com.example.cookbook.application.App
import com.example.cookbook.data.room.Database
import com.example.cookbook.data.room.RoomCategory
import com.example.cookbook.domain.cache.cahe_interfaces.ICategoriesCache
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.repository.IPictureRepo
import com.example.cookbook.domain.utils.network.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CategoriesCache : ICategoriesCache {
    @Inject
    lateinit var database: Database

    @Inject
    lateinit var picturesRepo: IPictureRepo

    init {
        App.instance.appComponent.inject(this)
    }

    override fun newData(
        api: IDataSource
    ): Single<List<Category>> {
        return api.getCategories()
            .flatMap { categories ->
                Single.fromCallable {
                    val inputCategories = categories.categories
                    val roomCategories = inputCategories.map { category ->

                        RoomCategory(
                            category.idCategory,
                            category.strCategory, category.strCategoryThumb ?: "",
                            category.strCategoryDescription ?: ""
                        )
                    }

                    database.categoriesDao.insert(roomCategories)


                    picturesRepo.getPicturesDataForDTO(inputCategories)
                        //   .observeOn(Schedulers.io()) // переключать на тот же io поток не надо
                        .subscribe({ roomPicture ->
                            try {
                                database.pictureDao.insert(roomPicture)
                            } catch (e: SQLiteConstraintException) {
                                println("Error to Save in data base")
                            }
                        }, {
                            println("Error to Save in smartphone")
                        })

                    inputCategories

                }
            }
    }

    override fun fromDataBaseData(): Single<List<Category>> {

        return Single.fromCallable {
            var out = database.categoriesDao.getAll().map { roomCategory ->
                Category(
                    roomCategory.idCategory,
                    roomCategory.strCategory,
                    roomCategory.strCategoryThumb,
                    roomCategory.strCategoryDescription
                )
            }
            out.forEach {
                val roomCategoryFind =
                    it.strCategory.let { database.categoriesDao.findByCategory(it) }
                it.strCategoryThumb = database.pictureDao
                    .findForCategory(roomCategoryFind.idCategory).local_path
            }
            out
        }
    }

}