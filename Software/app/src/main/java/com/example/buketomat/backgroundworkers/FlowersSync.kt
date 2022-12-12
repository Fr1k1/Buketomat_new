package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Flower

interface FlowersSync {
    fun AddFlowersToList(result : MutableList<Flower>)
}