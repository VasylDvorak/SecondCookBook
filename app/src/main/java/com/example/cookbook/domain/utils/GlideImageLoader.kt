package com.example.cookbook.domain.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cookbook.domain.image.IImageLoader
import com.example.cookbook.R

class GlideImageLoader :

    IImageLoader <ImageView > {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .placeholder(R.drawable.baseline_dining_24)
            .into(container)
    }
}
