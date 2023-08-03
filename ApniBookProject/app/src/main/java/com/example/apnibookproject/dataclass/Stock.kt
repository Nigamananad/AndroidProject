package com.example.apnibookproject.dataclass

import android.os.Parcel
import android.os.Parcelable

data class Stock(
    val id:String="",
    var tittle:String="",
    var quantity:String=""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(tittle)
        parcel.writeString(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stock> {
        override fun createFromParcel(parcel: Parcel): Stock {
            return Stock(parcel)
        }

        override fun newArray(size: Int): Array<Stock?> {
            return arrayOfNulls(size)
        }
    }
}
