package com.example.cookbook.domain.presenters.menu_fragment_presenters

import com.example.cookbook.domain.repository.MenItemView
import com.example.cookbook.domain.repository.retrofit.IMenuRepo
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.view.CategoriesView
import com.example.cookbook.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import javax.inject.Inject

class MenuPresenter : MvpPresenter<CategoriesView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var menuRepo: IMenuRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    class MenuListPresenter : MenListPresenter {
        val menus = mutableListOf<Menu>()
        override var itemClickListener: ((MenItemView) -> Unit)? = null
        override fun getCount() = menus.size
        override fun bindView(view: MenItemView) {
            val menu = menus[view.pos]
            menu.let { it.strMeal?.let { itOne -> view.setName(itOne) } }
            menu.let { it.strMealThumb?.let { itOne -> view.loadPicture(itOne) } }
        }
    }


    val menuListPresenter = MenuListPresenter()


    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    lateinit var callMenuRepo: Single<List<Menu>>
    fun loadMenu(currentCategory: Category) {
        callMenuRepo = menuRepo.getMenu(currentCategory)
        loadMenuJavaRx()
    }


    fun loadMenuJavaRx() {
        viewState.progressCircleVisible()
        callMenuRepo
            .observeOn(mainThreadScheduler)
            .subscribe({ menus ->
                if (menus.isNotEmpty()) {
                    viewState.progressCircleGone()
                    menuListPresenter.menus.apply {
                        clear()
                        addAll(menus)
                    }
                    viewState.updateList()
                } else {
                    showError()
                }
            }, {
                showError()
            })

        menuListPresenter.itemClickListener = { itemView ->
            val infoMenu = menuListPresenter.menus[itemView.pos]


            router.navigateTo(screen.aboutRecipe(infoMenu))
        }
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
