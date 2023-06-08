package com.example.cookbook.domain.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesView : MvpView {
    fun init()
    fun updateList()
    fun release()
    fun progressCircleGone()
    fun progressCircleVisible()
    fun showToastFragment()
}
