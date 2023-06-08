package com.example.cookbook.domain.repository.retrofit

import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.core.Single

interface IMenuRepo {
    fun getMenu (currentCategory : Category): Single<List<Menu>>
}
