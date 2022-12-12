package com.example.lyceumapp.json.subgroups

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class SubgroupInfoJson(
    @Json(name = "subgroup_id") val subgroupId: Int,
    @Json(name = "subgroup_name") val subgroupName: String,
    @Json(name = "class_id") val gradeId: Int,
    @Json(name = "class_number") val gradeNumber: Int,
    @Json(name = "class_letter") val gradeLetter: String,
    @Json(name = "school_id") val schoolId: Int,
    @Json(name = "school_name") val schoolName: String
): Parcelable {
    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(subgroupId)
        parcel?.writeString(subgroupName)
        parcel?.writeInt(gradeId)
        parcel?.writeInt(gradeNumber)
        parcel?.writeString(gradeLetter)
        parcel?.writeInt(schoolId)
        parcel?.writeString(schoolName)
    }

    companion object CREATOR: Parcelable.Creator<SubgroupInfoJson> {
        override fun createFromParcel(parcel: Parcel?): SubgroupInfoJson {
            return SubgroupInfoJson(parcel!!.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!)
        }

        override fun newArray(p0: Int) = arrayOfNulls<SubgroupInfoJson>(p0)
    }
}
