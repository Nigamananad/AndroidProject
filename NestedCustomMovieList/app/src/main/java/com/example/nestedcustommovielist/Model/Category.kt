package com.example.nestedcustommovielist.Model

data class Category(

    var catId:Int,
    var tittle:String,
    var movielist:MutableList<Movie>
)
