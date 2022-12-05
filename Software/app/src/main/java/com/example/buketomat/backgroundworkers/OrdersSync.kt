package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Order

interface OrdersSync {

    fun onOrdersReceived(result : MutableList<Order>)
}