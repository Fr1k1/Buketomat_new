package com.example.buketomat.models

import org.json.JSONObject

class OrderBouquet: Bouquet {
    var kolicina : Int

    constructor(bouquet: Bouquet) : super(bouquet.Id,bouquet.Name,bouquet.Description,bouquet.Flowers,bouquet.Price,bouquet.Picture)   // used for adding bouquets to order
    {
        this.kolicina = 1;
    }

    constructor(data :JSONObject) : super(data) {
        this.kolicina = data.getInt("kolicina");
    }
}