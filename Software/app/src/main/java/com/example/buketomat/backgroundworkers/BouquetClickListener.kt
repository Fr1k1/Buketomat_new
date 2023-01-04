package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.OrderBouquet

interface BouquetClickListener {
    fun addBouquetToOrder(orderItem: OrderBouquet)
    fun removeBouquetFromOrder(orderItem: OrderBouquet)
}