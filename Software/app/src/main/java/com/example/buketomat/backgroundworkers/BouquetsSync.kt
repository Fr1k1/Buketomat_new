package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Bouquet

interface BouquetsSync {
    fun onBouquetAdded(orderId : Int)
    fun onBouquetItemAdded()
    fun AddBouquetsToList(result:MutableList<Bouquet>)
}