package com.example.lyceumapp.json.schools

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class SchoolJson(
    @Json(name = "school_id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String,
    @Json(name = "is_using_double_week") val isUsingDoubleWeek: Boolean
    ): Parcelable, Comparable<SchoolJson> {
    override fun describeContents() = 0
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(address)
        val isUsingDoubleWeek = if(isUsingDoubleWeek) 1 else 0
        parcel.writeInt(isUsingDoubleWeek)
    }

    companion object CREATOR: Parcelable.Creator<SchoolJson> {
        override fun createFromParcel(parcel: Parcel?): SchoolJson {
            val id = parcel?.readInt()!!
            val name = parcel.readString()!!
            val address = parcel.readString()!!
            val isUniversity = parcel.readInt()==1
            return SchoolJson(id, name, address, isUniversity)
        }

        override fun newArray(size: Int) = arrayOfNulls<SchoolJson>(size)
    }

    override fun compareTo(other: SchoolJson): Int {
        return name.compareTo(other.name)
    }
}
