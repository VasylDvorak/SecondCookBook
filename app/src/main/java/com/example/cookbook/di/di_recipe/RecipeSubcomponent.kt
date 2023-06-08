package com.example.cookbook.di.di_recipe

import com.example.cookbook.di.di_recipe.module.RecipeModule
import com.example.cookbook.ui.recipe.RecipeFragment
import com.example.cookbook.domain.presenters.RecipePresenter
import dagger.Subcomponent

@RecipeScope
@Subcomponent(
    modules = [
        RecipeModule::class]
)

interface RecipeSubcomponent {
    fun inject(recipePresenter: RecipePresenter)
    fun inject(recipeFragment: RecipeFragment)

}