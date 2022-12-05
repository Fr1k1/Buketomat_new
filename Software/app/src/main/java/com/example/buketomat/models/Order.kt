package com.example.buketomat.models

import com.example.buketomat.entites.User
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Order {
    private val sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
    val Id: Int
    val OrderTime: Date
    val FinalPrice: Double
    val DeliveryTime: Date?
    lateinit var Bouquets: List<Bouquet>
    lateinit var User : User

    constructor(Id : Int ,FinalPrice: Double, DeliveryTime: Date? , User : User )
    {
        this.Id = Id
        this.FinalPrice = FinalPrice
        this.OrderTime = Calendar.getInstance().time
        this.DeliveryTime = DeliveryTime
        this.User = User
    }

    constructor(data : JSONObject)
    {
        Id = data.getInt("id")
        OrderTime = sdf.parse(data.getString("vrijeme"))

        FinalPrice = data.getDouble("ukupni_iznos")
        DeliveryTime = sdf.parse(data.getString("vrijeme_dostave"))
        //User = data.getString("korisnik_id")
    }
}