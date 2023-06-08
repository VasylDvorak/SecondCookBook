package com.example.cookbook.navigation

import com.github.terrakok.cicerone.Screen
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_categories.Category

interface IScreens {

    fun categories(): Screen
    fun aboutRecipe(infoMenu: Menu): Screen
    fun menu(currentCategory: Category): Screen
    fun playMovie(playMovie: String): Screen

}
