package com.example.filmforfriends

import android.media.Image
import java.io.Serializable



class Film(
    val id: Int,
    val name: Int,
    val image: Image
) : Serializable

class Image(
    val original: String
)