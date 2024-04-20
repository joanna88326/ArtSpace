package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Artwork

class DataSource {
    val artworks: List<Artwork> = listOf(
        Artwork(
            R.drawable.artwork1,
            "Mountains Sun Moon",
            "BiancaVanDijk",
            "2024"
        ),
        Artwork(
            R.drawable.artwork2,
            "Landscape Sun Nature",
            "regencygirl123",
            "2023"
        ),
        Artwork(
            R.drawable.artwork3,
            "Boho Art Sunset Mountains",
            "regencygirl123",
            "2021"
        ),
        Artwork(
            R.drawable.artwork4,
            "Mountain Sun Boho Style",
            "TianaZZ",
            "2022"
        ),
        Artwork(
            R.drawable.artwork5,
            "Boho Art Minimalism Bohemian Style Art",
            "TianaZZ",
            "2022"
        )
    )
}