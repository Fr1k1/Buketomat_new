package com.example.buketomat.entites

import org.json.JSONObject

class User{
    val email: String
    val username: String
    val password: String

    constructor(email : String , username : String , password : String)
    {
        this.email = email
        this.username = username
        this.password = password
    }


    constructor(data : JSONObject)
    {
        email = data.getString("email")
        username = data.getString("korime")
        password = data.getString("lozinka")
    }
    override fun toString(): String {
        return username
    }
}
// ToDo ako je preporuka da klasa sadrzi podatke onda je data class...nakon prve faze dodaj sve podatke o korisniku

