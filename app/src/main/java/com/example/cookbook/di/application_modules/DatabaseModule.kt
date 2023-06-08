package com.example.cookbook.di.application_modules

import androidx.room.Room
import com.example.cookbook.application.App
import com.example.cookbook.data.room.Database
import com.example.cookbook.domain.cache.CategoriesCache
import com.example.cookbook.domain.cache.MenuCache
import com.example.cookbook.domain.cache.PictureCache
import com.example.cookbook.domain.cache.RecipeCache
import com.example.cookbook.domain.cache.cahe_interfaces.IPictureCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app, Database::class.java,
        Database.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun roomCategoriesCache(): CategoriesCache = CategoriesCache()

    @Singleton
    @Provides
    fun roomMenuCache(): MenuCache = MenuCache()

    @Singleton
    @Provides
    fun roomRecipeCache(): RecipeCache = RecipeCache()

    @Singleton
    @Provides
    fun roomPictureCache(): PictureCache = PictureCache()


    @Singleton
    @Provides
    fun pictureCache(): IPictureCache = PictureCache()


}