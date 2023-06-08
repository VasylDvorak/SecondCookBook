package com.example.cookbook.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class PictureEnity(
    @Expose var idCategory: String,
    @Expose var strCategoryThumb: String,
    @Expose var local_path: String
) : Parcelable