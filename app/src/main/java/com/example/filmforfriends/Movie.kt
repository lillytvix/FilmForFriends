package com.example.filmforfriends

import android.media.Image
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieResults(
    @SerializedName("results")
    val results:List<Film> )

class Film(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview:String
):Serializable