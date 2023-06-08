package com.example.cookbook.domain.cache

import com.example.cookbook.application.App
import com.example.cookbook.domain.utils.network.api.IDataSource
import com.example.cookbook.data.room.Database
import com.example.cookbook.data.room.RoomRecipe
import com.example.cookbook.domain.cache.cahe_interfaces.IRecipeCache
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_recipe.Meal
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RecipeCache : IRecipeCache {
    @Inject
    lateinit var database: Database

    init {
        App.instance.appComponent.inject(this)
    }

    override fun newData(menu: Menu, api: IDataSource): Single<List<Meal>> {
        return menu.idMeal?.let { url ->
            api.getRecipe(url)
                .flatMap { recipe ->
                    Single.fromCallable {
                        val meals = recipe.meals
                        val roomRecipes = meals?.map {
                            RoomRecipe(
                                it.idMeal ?: "",
                                it?.dateModified ?: "",
                                it?.strArea ?: "",
                                it?.strCategory ?: "",
                                it?.strCreativeCommonsConfirmed ?: "",
                                it?.strDrinkAlternate ?: "",
                                it?.strImageSource ?: "",
                                it?.strIngredient1 ?: "",
                                it?.strIngredient10 ?: "",
                                it?.strIngredient11 ?: "",
                                it?.strIngredient12 ?: "",
                                it?.strIngredient13 ?: "",
                                it?.strIngredient14 ?: "",
                                it?.strIngredient15 ?: "",
                                it?.strIngredient16 ?: "",
                                it?.strIngredient17 ?: "",
                                it?.strIngredient18 ?: "",
                                it?.strIngredient19 ?: "",
                                it?.strIngredient2 ?: "",
                                it?.strIngredient20 ?: "",
                                it?.strIngredient3 ?: "",
                                it?.strIngredient4 ?: "",
                                it?.strIngredient5 ?: "",
                                it?.strIngredient6 ?: "",
                                it?.strIngredient7 ?: "",
                                it?.strIngredient8 ?: "",
                                it?.strIngredient9 ?: "",
                                it?.strInstructions ?: "",
                                it?.strMeal ?: "",
                                it?.strMealThumb ?: "",
                                it?.strMeasure1 ?: "",
                                it?.strMeasure10 ?: "",
                                it?.strMeasure11 ?: "",
                                it?.strMeasure12 ?: "",
                                it?.strMeasure13 ?: "",
                                it?.strMeasure14 ?: "",
                                it?.strMeasure15 ?: "",
                                it?.strMeasure16 ?: "",
                                it?.strMeasure17 ?: "",
                                it?.strMeasure18 ?: "",
                                it?.strMeasure19 ?: "",
                                it?.strMeasure2 ?: "",
                                it?.strMeasure20 ?: "",
                                it?.strMeasure3 ?: "",
                                it?.strMeasure4 ?: "",
                                it?.strMeasure5 ?: "",
                                it?.strMeasure6 ?: "",
                                it?.strMeasure7 ?: "",
                                it?.strMeasure8 ?: "",
                                it?.strMeasure9 ?: "",
                                it?.strSource ?: "",
                                it?.strTags ?: "",
                                it?.strYoutube ?: ""
                            )
                        }

                        database.recipeDao.insert(roomRecipes!!)
                        meals

                    }
                }
        }!!
    }


    override fun fromDataBaseData(menu: Menu): Single<List<Meal>> {
        return Single.fromCallable {

            val recipeFromDB = database.recipeDao.findRecipe(menu.idMeal).map {
                Meal(
                    it?.idMeal ?: "",
                    it?.dateModified ?: "",
                    it?.strArea ?: "",
                    it?.strCategory ?: "",
                    it?.strCreativeCommonsConfirmed ?: "",
                    it?.strDrinkAlternate ?: "",
                    it?.strImageSource ?: "",
                    it?.strIngredient1 ?: "",
                    it?.strIngredient10 ?: "",
                    it?.strIngredient11 ?: "",
                    it?.strIngredient12 ?: "",
                    it?.strIngredient13 ?: "",
                    it?.strIngredient14 ?: "",
                    it?.strIngredient15 ?: "",
                    it?.strIngredient16 ?: "",
                    it?.strIngredient17 ?: "",
                    it?.strIngredient18 ?: "",
                    it?.strIngredient19 ?: "",
                    it?.strIngredient2 ?: "",
                    it?.strIngredient20 ?: "",
                    it?.strIngredient3 ?: "",
                    it?.strIngredient4 ?: "",
                    it?.strIngredient5 ?: "",
                    it?.strIngredient6 ?: "",
                    it?.strIngredient7 ?: "",
                    it?.strIngredient8 ?: "",
                    it?.strIngredient9 ?: "",
                    it?.strInstructions ?: "",
                    it?.strMeal ?: "",
                    it?.strMealThumb ?: "",
                    it?.strMeasure1 ?: "",
                    it?.strMeasure10 ?: "",
                    it?.strMeasure11 ?: "",
                    it?.strMeasure12 ?: "",
                    it?.strMeasure13 ?: "",
                    it?.strMeasure14 ?: "",
                    it?.strMeasure15 ?: "",
                    it?.strMeasure16 ?: "",
                    it?.strMeasure17 ?: "",
                    it?.strMeasure18 ?: "",
                    it?.strMeasure19 ?: "",
                    it?.strMeasure2 ?: "",
                    it?.strMeasure20 ?: "",
                    it?.strMeasure3 ?: "",
                    it?.strMeasure4 ?: "",
                    it?.strMeasure5 ?: "",
                    it?.strMeasure6 ?: "",
                    it?.strMeasure7 ?: "",
                    it?.strMeasure8 ?: "",
                    it?.strMeasure9 ?: "",
                    it?.strSource ?: "",
                    it?.strTags ?: "",
                    it?.strYoutube ?: ""
                )
            }
            recipeFromDB
        }
    }
}