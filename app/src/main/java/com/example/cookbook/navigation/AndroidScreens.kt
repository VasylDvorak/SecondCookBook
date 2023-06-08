package com.example.cookbook.navigation

import android.os.Bundle
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.ui.categories.CategoriesFragment
import com.example.cookbook.ui.menu.CURRENT_CATEGORY
import com.example.cookbook.ui.menu.MenuFragment
import com.example.cookbook.ui.play_movie.PLAY_MOVIE
import com.example.cookbook.ui.play_movie.PlayMovieFragment
import com.example.cookbook.ui.recipe.CURRENT_RECIPE
import com.example.cookbook.ui.recipe.RecipeFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun categories() = FragmentScreen { CategoriesFragment.newInstance() }

    override fun menu(currentCategory: Category): Screen = FragmentScreen {
        MenuFragment.newInstance(Bundle().apply {
            putParcelable(
                CURRENT_CATEGORY,
                currentCategory
            )
        })
    }

    override fun aboutRecipe(infoMenu: Menu): Screen = FragmentScreen {
        RecipeFragment.newInstance(Bundle().apply {
            putParcelable(
                CURRENT_RECIPE,
                infoMenu
            )
        })
    }

    override fun playMovie(playMovie: String): Screen = FragmentScreen {
        PlayMovieFragment.newInstance(Bundle().apply {
            putString(
                PLAY_MOVIE,
                playMovie
            )
        })
    }


}



