package com.example.buketomat.models

data class Bouquet (

    val Id : Int,
    val Name : String,
    var Description : String,
    var Flowers : ArrayList<Flower>,
    var Price : Double
    )
