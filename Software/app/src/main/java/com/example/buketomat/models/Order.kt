package com.example.buketomat.models

import java.util.Date

class Order (

    val Id : Int,
    val OrderTime : Date,
    val FinalPrice : Double,
    val DeliveryTime : Date?,
    val Bouquets : List<Bouquet>
    //val User : User               --> add later when merged into main branch


)