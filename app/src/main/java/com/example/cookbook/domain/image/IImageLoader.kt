package com.example.cookbook.domain.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
