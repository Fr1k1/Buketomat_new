package com.example.buketomat.models

import org.json.JSONObject

class FlowerBouquet : Flower { // jer flower nema kolicinu, a u tablici cvijet_buket je i atribut kolicina
    var kolicina : Int

    constructor(flower: Flower, kolicina : Int) : super(flower.Id , flower.Name, flower.Price)
    {
        this.kolicina = kolicina;
    }

    constructor(data : JSONObject) : super(data) {
        this.kolicina = data.getInt("kolicina");
    }
}