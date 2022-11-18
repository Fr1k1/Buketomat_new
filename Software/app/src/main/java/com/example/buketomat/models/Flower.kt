package com.example.buketomat.models

data class Flower(

    val Id : Int,
    val Name : String,
    val Colors : ArrayList<String>, //maybe create class color later
    var Picture : String,
    var Price : Double

)