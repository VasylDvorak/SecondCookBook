package com.example.cookbook.domain.repository

import com.example.cookbook.domain.entity.entity_categories.Category
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Single.create
import io.reactivex.rxjava3.core.Single.just
import io.reactivex.rxjava3.core.Single.never
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.internal.operators.single.SingleEquals
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.jetbrains.annotations.TestOnly

interface ICategoriesRepo {
    fun getCategories (): Single<List<Category>>



}
