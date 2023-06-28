package com.example.cookbook.domain.presenters

import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.presenters.categories_presenters.CategoriesPresenter
import com.example.cookbook.domain.repository.ICategoriesRepo
import com.example.cookbook.domain.view.CategoriesView
import com.example.cookbook.navigation.IScreens
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CategoriesPresenterTest {

    private lateinit var presenter: CategoriesPresenter

    private var mainThreadScheduler: TestScheduler = TestScheduler()

    @Mock
    lateinit var categoriesRepo: ICategoriesRepo

    @Mock
    lateinit var viewState: CategoriesView

    @Mock
    lateinit var screen: IScreens


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CategoriesPresenter().apply {
            mainThreadScheduler = this@CategoriesPresenterTest.mainThreadScheduler
            categoriesRepo = this@CategoriesPresenterTest.categoriesRepo
            screen = this@CategoriesPresenterTest.screen
        }
    }

    @Test
    fun loadCategoriesJavaRx_Test() {
        presenter.callCategoriesRepo = Single.just(listOf(Category()))
        presenter.loadCategoriesJavaRx()
        verify(categoriesRepo, times(0)).getCategories()
    }

    @Test
    fun loadCategoriesJavaRx_Test_ResponseIsEmpty() {
        val response = Single.just(listOf<Category>())
        val categories = mock(List::class.java) as List<Category>
        `when`(categories.isNotEmpty()).thenReturn(false)
        presenter.callCategoriesRepo = response
        presenter.loadCategoriesJavaRx()
        assertTrue(response.blockingGet().isEmpty())
        verify(viewState, times(0)).progressCircleGone()
    }

    @Test
    fun loadCategoriesJavaRx_Test_ResponseIsNotEmpty() {

        val response =
            Single.just(listOf(Category(idCategory = "one"), Category(idCategory = "two")))
        val categories = mock(List::class.java) as List<Category>
        `when`(categories.isNotEmpty()).thenReturn(true)
        presenter.callCategoriesRepo = response
        presenter.loadCategoriesJavaRx()
        assertNotEquals(response.blockingGet().size, 0)
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
