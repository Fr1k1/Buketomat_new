package com.example.buketomat.models

import org.json.JSONObject

class Bouquet {

    val Id: Int
    val Name: String
    var Description: String
    lateinit var Flowers: ArrayList<Flower>
    var Price: Double
    //var Picture:String

    constructor(
        Id: Int,
        Name: String,
        Description: String,
        Flowers: ArrayList<Flower>,
        Price: Double,
        //Picture:String
    ) {
        this.Id = Id
        this.Name = Name
        this.Description = Description
        this.Price = Price
        this.Flowers = Flowers
        //this.Picture=Picture
    }

    constructor(data: JSONObject) {
        Id = data.getInt("id")
        Name = data.getString("naziv")
        Description = data.getString("opis")
        Price = data.getDouble("cijena")
        //Picture=data.getString("slika")
        //Flowers=data.getString("cvjetovi")
    }
}

