package com.example.buketomat.models

import com.example.buketomat.entites.User
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

open class Flower { // open da se moze napravit klasa FlowerBouquet jer je inace final

    val Id: Int
    val Name: String
    var Price: Double
    // val Colors: ArrayList<String>
    // var Picture: String

    constructor (Id: Int, Name: String, Price: Double) {
        this.Id = Id
        this.Name = Name
        this.Price = Price
    }

    constructor(data: JSONObject) {
        Id = data.getInt("id")
        Name = data.getString("naziv")
        Price = data.getDouble("cijena")
    }
}