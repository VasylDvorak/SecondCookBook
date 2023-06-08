package com.example.cookbook.domain.view

interface IItemView {
    var pos: Int
}

interface CategoryItemView : IItemView {
    fun setName(text: String)
    fun loadPicture(url: String)
}
