package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Order

interface OrdersSync {

    fun AddOrdersToList(result : MutableList<Order>)

}