package com.example.buketomat.backgroundworkers

import com.example.buketomat.models.Bouquet

interface BouquetsSync {

    fun AddBouquetsToList(result:MutableList<Bouquet>)
}