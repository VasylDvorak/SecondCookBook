package com.example.cookbook.domain.repository


interface MenuItemView {
    var pos: Int
}
interface MenItemView : MenuItemView  {
    fun setName (text: String )
    fun loadPicture(url:String)
}