package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Bouquet

interface BouquetClickListener {
    fun addBouquetToOrder(bouquet: Bouquet)
}