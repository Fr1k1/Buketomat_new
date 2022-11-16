package com.example.buketomat.entites

class User(val email: String, val username: String, val password: String) {
    override fun toString(): String {
        return username
    }
}
// ToDo ako je preporuka da klasa sadrzi podatke onda je data class...nakon prve faze dodaj sve podatke o korisniku

