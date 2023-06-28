package com.example.cookbook.domain.presenters

import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.presenters.menu_fragment_presenters.MenuPresenter
import com.example.cookbook.domain.repository.retrofit.IMenuRepo
import com.example.cookbook.domain.view.CategoriesView
import com.example.cookbook.navigation.IScreens
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MenuPresenterTest {

    private lateinit var presenter: MenuPresenter

    private var mainThreadScheduler: TestScheduler = TestScheduler()

    @Mock
    lateinit var menuRepo: IMenuRepo

    @Mock
    lateinit var viewState: CategoriesView

    @Mock
    lateinit var screen: IScreens


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MenuPresenter().apply {
            mainThreadScheduler = this@MenuPresenterTest.mainThreadScheduler
            menuRepo = this@MenuPresenterTest.menuRepo
            screen = this@MenuPresenterTest.screen
        }
    }

    @Test
    fun loadMenuJavaRx_Test() {
        presenter.callMenuRepo = Single.just(listOf(Menu()))
        presenter.loadMenuJavaRx()
        verify(menuRepo, times(0)).getMenu(Category())
    }

    @Test
    fun loadMenuJavaRx_Test_ResponseIsEmpty() {
        val response = Single.just(listOf<Menu>())
        val menus = mock(List::class.java) as List<Menu>
        `when`(menus.isNotEmpty()).thenReturn(false)
        presenter.callMenuRepo = response
        presenter.loadMenuJavaRx()
        assertTrue(response.blockingGet().isEmpty())
        verify(viewState, times(0)).showToastFragment()
    }

    @Test
    fun loadMenuJavaRx_Test_ResponseIsNotEmpty() {

        val response =
            Single.just(listOf(Menu(idMeal = "one"), Menu(idMeal = "two")))
        val menus = mock(List::class.java) as List<Menu>
        `when`(menus.isNotEmpty()).thenReturn(true)
        presenter.callMenuRepo = response
        presenter.loadMenuJavaRx()
        assertNotEquals(response.blockingGet().size, 0)
        verify(viewState, never()).progressCircleGone()
    }

    @Test
    fun onFirstViewAttach_Test() {
        presenter.onFirstViewAttach()
        verify(screen, never()).menu(Category())
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

}
