package com.example.cookbook.domain.presenters.categories_presenters

import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.repository.ICategoriesRepo
import com.example.cookbook.domain.view.CategoriesView
import com.example.cookbook.domain.view.CategoryItemView
import com.example.cookbook.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class CategoriesPresenter : MvpPresenter<CategoriesView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var categoriesRepo: ICategoriesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    inner class CategoriesListPresenter : ICategoryListPresenter {
        val categories = mutableListOf<Category>()
        override var itemClickListener: ((CategoryItemView) -> Unit)? = null
        override fun getCount() = categories.size
        override fun bindView(view: CategoryItemView) {
            val category = categories[view.pos]
            category.apply {
                strCategory.let { view.setName(it) }
                strCategoryThumb?.let {
                    view.loadPicture(it)
                }
            }

        }
    }


    val categoriesListPresenter = CategoriesListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadCategories()

        categoriesListPresenter.itemClickListener = { itemView ->
            val currentCategory = categoriesListPresenter.categories[itemView.pos]

            currentCategory.let {
                router.navigateTo(screen.menu(currentCategory))
            }
        }
    }

    fun loadCategories() {
        viewState.progressCircleVisible()
        categoriesRepo.getCategories()
            .observeOn(mainThreadScheduler)
            .subscribe({ categories ->
                if (categories.size != 0) {
                    viewState.progressCircleGone()
                    categoriesListPresenter.categories.apply {
                        clear()
                        addAll(categories)
                    }
                    viewState.updateList()
                } else {
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


    fun showError() {
        viewState.apply {
            progressCircleGone()
            showToastFragment()
        }
    }
}
