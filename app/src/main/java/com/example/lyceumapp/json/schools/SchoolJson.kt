package com.example.lyceumapp.json.schools

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class SchoolJson(
    @Json(name = "school_id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String
): Parcelable {
    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(name)
        parcel?.writeString(address)
    }

    companion object CREATOR: Parcelable.Creator<SchoolJson> {
        override fun createFromParcel(parcel: Parcel?): SchoolJson {
            return SchoolJson(
                parcel?.readInt()!!,
                parcel?.readString()!!,
                parcel?.readString()!!
            )
        }

        override fun newArray(size: Int) = arrayOfNulls<SchoolJson>(size)
    }
}
