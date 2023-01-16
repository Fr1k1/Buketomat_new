package com.example.buketomat.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

open class Bouquet : Parcelable {

    val Id: Int
    val Name: String
    var Description: String
    var Flowers: ArrayList<Flower> = arrayListOf()
    var Price: Double
    var Picture: String

    constructor(
        Id: Int,
        Name: String,
        Description: String,
        Flowers: ArrayList<Flower>,
        Price: Double,
        Picture: String
    ) {
        this.Id = Id
        this.Name = Name
        this.Description = Description
        this.Price = Price
        this.Flowers = Flowers
        this.Picture = Picture
    }

    constructor(data: JSONObject) {
        Id = data.getInt("id")
        Name = data.getString("naziv")
        Description = data.getString("opis")
        Price = data.getDouble("cijena")
        Picture = data.getString("slika")
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readArrayList(Flower::class.java.classLoader) as ArrayList<Flower>,
        parcel.readDouble(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Name)
        parcel.writeString(Description)
        parcel.writeList(Flowers)
        parcel.writeDouble(Price)
        parcel.writeString(Picture)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bouquet> {
        override fun createFromParcel(parcel: Parcel): Bouquet {
            return Bouquet(parcel)
        }

        override fun newArray(size: Int): Array<Bouquet?> {
            return arrayOfNulls(size)
        }
    }
}

