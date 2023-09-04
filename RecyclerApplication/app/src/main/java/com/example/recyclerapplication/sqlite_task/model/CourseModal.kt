package com.example.recyclerapplication.sqlite_task.model

import android.os.Parcel
import android.os.Parcelable

class CourseModal(
    var id: String,
    var courseName: String,
    var courseDuration: String,
    var courseTracks: String,
    var courseDescription: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(courseName)
        parcel.writeString(courseDuration)
        parcel.writeString(courseTracks)
        parcel.writeString(courseDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseModal> {
        override fun createFromParcel(parcel: Parcel): CourseModal {
            return CourseModal(parcel)
        }

        override fun newArray(size: Int): Array<CourseModal?> {
            return arrayOfNulls(size)
        }
    }
}
