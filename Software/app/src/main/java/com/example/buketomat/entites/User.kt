package com.example.buketomat.entites

import android.os.Parcel
import android.os.Parcelable
import com.example.buketomat.models.Flower
import org.json.JSONObject

class User : Parcelable {
    val id: Int
    val email: String
    val username: String
    val password: String
    val name: String
    val surname: String
    val address: String

    constructor(
        id: Int,
        name: String,
        surname: String,
        address: String,
        email: String,
        username: String,
        password: String
    ) {
        this.id = id
        this.name = name
        this.surname = surname
        this.address = address
        this.email = email
        this.username = username
        this.password = password
    }

    constructor(data: JSONObject) {
        this.id = data.getInt("id")
        name = data.getString("ime")
        surname = data.getString("prezime")
        address = data.getString("adresa")
        email = data.getString("email")
        username = data.getString("korime")
        password = data.getString("lozinka")
    }

    override fun toString(): String {
        return username
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(username)
        parcel.writeString(password)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}

