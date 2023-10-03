package com.example.adduserproject.volley_library_example.model

import android.os.Parcel
import android.os.Parcelable

data class CourseRVModal(
    var courseName: String,
    var courseImg: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseName)
        parcel.writeString(courseImg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseRVModal> {
        override fun createFromParcel(parcel: Parcel): CourseRVModal {
            return CourseRVModal(parcel)
        }

        override fun newArray(size: Int): Array<CourseRVModal?> {
            return arrayOfNulls(size)
        }
    }
}
