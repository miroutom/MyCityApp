package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @StringRes val title: Int,
    @DrawableRes val image: Int,
    val places: List<Place>
)
