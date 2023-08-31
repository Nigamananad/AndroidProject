package com.example.recyclerapplication.sqlite_task.model

import android.os.Parcel
import android.os.Parcelable

class CourseModal(
    var courseName: String,
    var courseDuration: String,
    var courseTracks: String,
    var courseDescription: String
) : Parcelable {
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseName)
        parcel.writeString(courseDuration)
        parcel.writeString(courseTracks)
        parcel.writeString(courseDescription)
        parcel.writeInt(id)
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
