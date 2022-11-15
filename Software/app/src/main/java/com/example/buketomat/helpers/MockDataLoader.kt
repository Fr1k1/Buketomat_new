package com.example.buketomat.helpers

import com.example.buketomat.entites.User

object MockDataLoader {

    fun getDemoData(): List<User> = listOf(
        User("mmarkic1@gmail.com", "mmarkic", "1234"),
        User("iivic1@gmail.com", "iivic", "5678"),
        User("ggabric1@gmail.com", "ggabric", "1111"),
    )
}