package com.example.cookbook.di.application_modules

import android.widget.ImageView
import com.example.cookbook.domain.cache.PictureCache
import com.example.cookbook.domain.image.IImageLoader
import com.example.cookbook.domain.repository.ICategoriesRepo
import com.example.cookbook.domain.repository.IPictureRepo
import com.example.cookbook.domain.repository.retrofit.PictureRepo
import com.example.cookbook.domain.repository.retrofit.RetrofitCategoriesRepo
import com.example.cookbook.domain.utils.GlideImageLoader
import com.example.cookbook.domain.utils.network.INetworkStatus
import com.example.cookbook.domain.utils.network.api.IDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()

    @Singleton
    @Provides
    fun pictureRepo(): IPictureRepo{
        return PictureRepo()
    }
}