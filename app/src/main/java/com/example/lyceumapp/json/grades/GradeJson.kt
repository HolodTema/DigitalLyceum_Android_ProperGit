package com.example.lyceumapp.json.grades

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class GradeJson(
    @Json(name = "class_id") val id: Int,
    @Json(name = "number") val number: Int,
    @Json(name = "letter") val letter: String,
    @Json(name = "class_type") val gradeType: String
): Parcelable {
    override fun toString() = number.toString()+letter
    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeInt(number)
        parcel?.writeString(letter)
        parcel?.writeString(gradeType)
    }

    companion object CREATOR: Parcelable.Creator<GradeJson> {
        override fun createFromParcel(parcel: Parcel?): GradeJson {
            return GradeJson(parcel?.readInt()!!, parcel.readInt(), parcel.readString()!!, parcel.readString()!!)
        }

        override fun newArray(size: Int) = arrayOfNulls<GradeJson>(size)
    }
}