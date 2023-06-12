package com.example.cookbook.domain.presenters

import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_recipe.Meal
import com.example.cookbook.domain.repository.retrofit.IRecipeRepo
import com.example.cookbook.domain.utils.FormIngredientsInstruction
import com.example.cookbook.domain.view.RecipeView
import com.example.cookbook.navigation.IScreens
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class RecipePresenterTest {

    private lateinit var presenter: RecipePresenter

    private var mainThreadScheduler: TestScheduler = TestScheduler()

    @Mock
    lateinit var recipeRepo: IRecipeRepo

    @Mock
    lateinit var viewState: RecipeView

    @Mock
    lateinit var screen: IScreens

    @Mock
    lateinit var formIngredientsInstruction: FormIngredientsInstruction

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RecipePresenter().apply {
            mainThreadScheduler = this@RecipePresenterTest.mainThreadScheduler
            recipeRepo = this@RecipePresenterTest.recipeRepo
            screen = this@RecipePresenterTest.screen
            formIngredientsInstruction = this@RecipePresenterTest.formIngredientsInstruction
        }
    }

    @Test
    fun loadRecipeJavaRx_Test() {
        presenter.callRecipeRepo = Single.just(listOf(Meal()))
        presenter.loadRecipeJavaRx()
        verify(recipeRepo, times(0)).getRecipes(Menu())
    }

    @Test
    fun loadRecipeJavaRx_Test_ResponseIsEmpty() {

        val response = Single.just(listOf<Meal>())
        val recipe = mock(List::class.java) as List<Meal>
        `when`(recipe.isNotEmpty()).thenReturn(false)
        presenter.callRecipeRepo = response
        presenter.loadRecipeJavaRx()
        assertTrue(response.blockingGet().isEmpty())
    }

    @Test
    fun loadRecipeJavaRx_Test_ResponseIsNotEmpty() {
        val response =
            Single.just(listOf(Meal(idMeal = "one"), Meal(idMeal = "two")))
        val recipe = mock(List::class.java) as List<Meal>
        `when`(recipe.isNotEmpty()).thenReturn(true)
        presenter.callRecipeRepo = response
        presenter.loadRecipeJavaRx()
        assertNotEquals(response.blockingGet().size, 0)
    }

    @Test
    fun onFirstViewAttach_Test() {
        presenter.onFirstViewAttach()
        verify(viewState, never()).init()
    }

    @Test
    fun onDestroy_Test() {
        presenter.onDestroy()
        verify(viewState, never()).release()
    }

    @Test
    fun showError_Test() {
        presenter.showError()
        verify(viewState, never()).apply {
            progressCircleGone()
            showToastFragment()
        }
    }

    @Test
    fun showInstructionAndIngredients_Test() {

        val testRecipe = Meal()
        testRecipe.strInstructions = "test_text"
        presenter.currentRecipe = testRecipe
        presenter.showInstructionAndIngredients()

        val inOrder = inOrder(formIngredientsInstruction)
        inOrder.verify(formIngredientsInstruction)
            .formInstructionText(testRecipe.strInstructions ?: "")
        inOrder.verify(formIngredientsInstruction).ingredientsList(testRecipe)
    }
}
