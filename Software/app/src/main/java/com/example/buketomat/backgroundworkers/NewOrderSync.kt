package com.example.buketomat.backgroundworkers

interface NewOrderSync {
    fun onOrderAdded(orderId : Int)

    fun onOrderItemAdded()
}