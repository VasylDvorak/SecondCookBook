package com.example.cookbook.domain.presenters

import com.example.cookbook.domain.view.MainView
import com.example.cookbook.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter () :
    MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens
    override fun onFirstViewAttach () {
        super.onFirstViewAttach()
        router.replaceScreen(screens.categories())
    }

    fun backClicked () {
        router.exit()
    }
}
