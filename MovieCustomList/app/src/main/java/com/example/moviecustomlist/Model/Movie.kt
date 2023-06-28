package com.example.moviecustomlist.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var movieid: Int,
    var movieName: String,
    var category: String,
    var rating: Float,
    var year: String,
    var image: Int
):Parcelable
