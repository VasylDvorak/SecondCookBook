package com.example.cookbook.domain.presenters.menu_fragment_presenters

import com.example.cookbook.domain.repository.MenuItemView
import com.example.cookbook.domain.repository.MenItemView


interface MenuListPresenter <V : MenuItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface MenListPresenter : MenuListPresenter<MenItemView>
