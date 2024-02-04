package com.example.mycity.data

import com.example.mycity.data.attractions
import com.example.mycity.data.cafes
import com.example.mycity.data.cinemas
import com.example.mycity.R
import com.example.mycity.data.parks

object Categories {
    val categories = listOf(
        Category(
            R.string.cafes,
            R.drawable.baseline_coffee_24,
            cafes,
        ),
        Category(
            R.string.cinemas,
            R.drawable.baseline_video_camera_back_24,
            cinemas
        ),
        Category(
            R.string.parks,
            R.drawable.baseline_park_24,
            parks
        ),
        Category(
            R.string.attractions,
            R.drawable.baseline_attractions_24,
            attractions
        )
    )

    val currentCategory = categories[0]
    val currentPlace = currentCategory.places[0]
}