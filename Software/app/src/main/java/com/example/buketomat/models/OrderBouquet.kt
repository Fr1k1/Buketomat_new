package com.example.buketomat.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject


class OrderBouquet : Bouquet, Parcelable {
    var kolicina : Int

    constructor(bouquet: Bouquet) : super(bouquet.Id,bouquet.Name,bouquet.Description,bouquet.Flowers,bouquet.Price,bouquet.Picture)   // used for adding bouquets to order
    {
        this.kolicina = 1
    }

    constructor(data :JSONObject) : super(data) {
        this.kolicina = data.getInt("kolicina")
    }

    constructor(parcel: Parcel) : super(parcel) {
        kolicina = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeInt(kolicina)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderBouquet> {
        override fun createFromParcel(parcel: Parcel): OrderBouquet {
            return OrderBouquet(parcel)
        }

        override fun newArray(size: Int): Array<OrderBouquet?> {
            return arrayOfNulls(size)
        }
    }
}