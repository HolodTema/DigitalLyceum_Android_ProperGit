package com.example.lyceumapp.json.teachers

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class TeacherJson: Parcelable(
    @Json(name = "teacher_id") val id: Int,
    @Json(name = "name") val name: String
) {
    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR: Parcelable.Creator<TeacherJson> {
        override fun createFromParcel(parcel: Parcel?) = TeacherJson(parcel)

        override fun newArray(p0: Int) = arrayOfNulls<TeacherJson>(p0)
    }
}
