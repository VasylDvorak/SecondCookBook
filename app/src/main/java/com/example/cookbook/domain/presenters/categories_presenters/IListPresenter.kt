package com.example.cookbook.domain.presenters.categories_presenters

import com.example.cookbook.domain.view.IItemView
import com.example.cookbook.domain.view.CategoryItemView

interface IListPresenter <V : IItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface ICategoryListPresenter : IListPresenter<CategoryItemView>

