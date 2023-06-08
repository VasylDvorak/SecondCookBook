package com.example.cookbook.di.di_menu

import com.example.cookbook.di.di_menu.module.MenuModule
import com.example.cookbook.di.di_recipe.RecipeSubcomponent
import com.example.cookbook.ui.menu.MenuRVAdapter
import com.example.cookbook.domain.presenters.menu_fragment_presenters.MenuPresenter
import dagger.Subcomponent

@MenuScope
@Subcomponent(
    modules = [
        MenuModule::class]
)

interface MenuSubcomponent {
    fun recipeSubcomponent(): RecipeSubcomponent
    fun inject(menuPresenter: MenuPresenter)
    fun inject(menuRVAdapter: MenuRVAdapter)
}