package com.example.cookbook.domain.view

import android.text.SpannableStringBuilder
import com.example.cookbook.domain.entity.entity_recipe.Meal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RecipeView : MvpView {
    fun init()
    fun release()
    fun showRecipe(currentRecipe: Meal)
    fun showToastFragment()
    fun showIngredients(text: SpannableStringBuilder)
    fun showInstruction(text: SpannableStringBuilder)
    fun progressCircleGone()
    fun progressCircleVisible()
}
