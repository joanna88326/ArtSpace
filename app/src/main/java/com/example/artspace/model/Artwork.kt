package com.example.artspace.model

import androidx.annotation.DrawableRes

data class Artwork(
    @DrawableRes val imageResId: Int,
    val title: String,
    val artist: String,
    val year: String
)
