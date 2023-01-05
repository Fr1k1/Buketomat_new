package com.example.buketomat.models

import android.util.Log
import com.example.buketomat.entites.User
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Order {
    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    private val sdfOut: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
    val Id: Int
    val OrderTime: Date
    val FinalPrice: Double
    val DeliveryTime: Date?
    val DeliveryLocation : String
    lateinit var Bouquets: List<Bouquet>
    lateinit var User : User

    constructor(Id : Int ,FinalPrice: Double, DeliveryTime: Date? ,DeliveryLocation : String, User : User )
    {
        this.Id = Id
        this.FinalPrice = FinalPrice
        this.OrderTime = Calendar.getInstance().time
        this.DeliveryTime = DeliveryTime
        this.DeliveryLocation = DeliveryLocation
        this.User = User
    }

    constructor(data : JSONObject)
    {
        Id = data.getInt("id")
        OrderTime = sdf.parse(data.getString("vrijeme"))
        Log.i("SDF",OrderTime.toString());
        FinalPrice = data.getDouble("ukupni_iznos")
        DeliveryLocation = data.getString("adresa_dostave")
        DeliveryTime = sdf.parse(data.getString("vrijeme_dostave"))
        //User = data.getString("korisnik_id")
    }

    fun getDeliveryDate(dbFormat : Boolean = false) : String
    {
        return if(!dbFormat)
            sdfOut.format(DeliveryTime)
        else
            sdf.format(DeliveryTime)
    }

    fun getOrderDate(dbFormat : Boolean = false) : String
    {
        return if(!dbFormat)
            sdfOut.format(OrderTime)
        else
            sdf.format(OrderTime)
    }

}