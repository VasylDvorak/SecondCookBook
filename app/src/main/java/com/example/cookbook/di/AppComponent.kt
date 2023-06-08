package com.example.cookbook.di

import com.example.cookbook.di.application_modules.*
import com.example.cookbook.di.di_categories.CategoriesSubcomponent
import com.example.cookbook.domain.cache.CategoriesCache
import com.example.cookbook.domain.cache.MenuCache
import com.example.cookbook.domain.cache.PictureCache
import com.example.cookbook.domain.cache.RecipeCache
import com.example.cookbook.domain.repository.retrofit.RetrofitCategoriesRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitMenuRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitRecipeRepo
import com.example.cookbook.ui.main_activity.MainActivity
import com.example.cookbook.domain.presenters.MainPresenter
import com.example.cookbook.domain.repository.retrofit.PictureRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun categoriesSubcomponent(): CategoriesSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(retrofitCategoriesRepo: RetrofitCategoriesRepo)
    fun inject(roomCategoriesCache: CategoriesCache)

    fun inject(retrofitMenuRepo: RetrofitMenuRepo)
    fun inject(roomMenuCache: MenuCache)

    fun inject(retrofitRecipeRepo: RetrofitRecipeRepo)
    fun inject(roomRecipeCache: RecipeCache)

    fun inject(roomPictureCache: PictureCache)

    fun inject(pictureRepo: PictureRepo)


}