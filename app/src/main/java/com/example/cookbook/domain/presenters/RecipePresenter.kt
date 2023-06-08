package com.example.cookbook.domain.presenters

import com.example.cookbook.domain.repository.retrofit.IRecipeRepo
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_recipe.Meal
import com.example.cookbook.navigation.IScreens
import com.example.cookbook.domain.utils.FormIngredientsInstruction
import com.example.cookbook.domain.view.RecipeView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject


class RecipePresenter : MvpPresenter<RecipeView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var recipeRepo: IRecipeRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    private var currentRecipe: Meal = Meal()
    private val formIngredientsInstruction = FormIngredientsInstruction()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }



    fun loadRecipe(currentItemMenu: Menu) {
        viewState.progressCircleVisible()
        recipeRepo.getRecipes(currentItemMenu)
            .observeOn(mainThreadScheduler)
            .subscribe({ recipe ->
                if(recipe.size !=0){
                    viewState.progressCircleGone()
                    currentRecipe = recipe.get(0)

                    viewState.showRecipe(currentRecipe)
                    showIngredients()
                    showInstruction()
            }     else{
                    showError()
            }
    }, {
                showError()
            })
    }


    override fun onDestroy() {
        super.onDestroy()

        viewState.release()
    }


    private fun showInstruction() {
        viewState.showInstruction(formIngredientsInstruction
            .formInstructionText(currentRecipe.strInstructions?:""))
    }
   private fun showIngredients() {
        viewState.showIngredients(formIngredientsInstruction.ingredientsList(currentRecipe))
    }

    fun playMovie() {
        currentRecipe.strYoutube?.let{
            router.navigateTo(screen.playMovie(it))
        }
    }

    fun showError(){
        viewState.apply{
            progressCircleGone()
            showToastFragment()
        }
    }
}