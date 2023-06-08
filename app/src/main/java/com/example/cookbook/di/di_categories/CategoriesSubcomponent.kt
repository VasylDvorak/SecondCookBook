package com.example.cookbook.di.di_categories


import com.example.cookbook.di.di_categories.module.CategoriesModule
import com.example.cookbook.di.di_menu.MenuSubcomponent
import com.example.cookbook.ui.categories.CategoriesRVAdapter
import com.example.cookbook.domain.presenters.categories_presenters.CategoriesPresenter
import dagger.Subcomponent

@CategoriesScope
@Subcomponent(
    modules = [
        CategoriesModule::class
    ]
)
interface CategoriesSubcomponent {
    fun menuSubcomponent(): MenuSubcomponent
    fun inject(categoriesPresenter: CategoriesPresenter)
    fun inject(categoriesRVAdapter: CategoriesRVAdapter)
}