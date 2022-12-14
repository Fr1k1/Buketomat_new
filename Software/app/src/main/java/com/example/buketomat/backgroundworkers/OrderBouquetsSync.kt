package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.OrderBouquet

interface OrderBouquetsSync {
    fun showOrderItems(result : MutableList<OrderBouquet>)
}