package com.example.buketomat.models

import org.json.JSONObject

class OrderBouquet: Bouquet {
    val kolicina : Int

    constructor(data :JSONObject) : super(data) {
        this.kolicina = data.getInt("kolicina");
    }
}