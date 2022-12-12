package com.example.lyceumapp.json.lessons

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.lyceumapp.json.teachers.TeacherJson
import com.squareup.moshi.Json

@Entity
class LessonJson(
    @PrimaryKey(autoGenerate = true) @Json(name = "lesson_id") var id: Int,
    @Json(name = "name") var name: String,
    @Ignore @Json(name = "start_time") val startTime: LessonTimeInterval,
    @Ignore @Json(name = "end_time") val endTime: LessonTimeInterval,
    @Json(name = "week") var week: Int,
    @Json(name = "weekday") var weekday: Int,
    @Json(name = "teacher") var teacher: TeacherJson
): Parcelable, Comparable<LessonJson> {
    var startHour = startTime.hour
    var startMinute = startTime.minute
    var endHour = endTime.hour
    var endMinute = endTime.minute


    constructor(id: Int, name: String, startHour: Int, startMinute: Int, endHour: Int, endMinute: Int, week: Int, weekday: Int, teacherId: Int): this(
        id, name, LessonTimeInterval(startHour, startMinute), LessonTimeInterval(endHour, endMinute), week, weekday, teacherId
    )

    override fun compareTo(other: LessonJson): Int {
        return if(startHour>other.startHour) 1
        else if(startHour==other.startHour) {

            if(startMinute>other.startMinute) 1
            else if(startMinute==other.startMinute) 0
            else -1

        }
        else -1
    }

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(startHour)
        parcel.writeInt(startMinute)
        parcel.writeInt(endHour)
        parcel.writeInt(endMinute)
        parcel.writeInt(week)
        parcel.writeInt(weekday)
        parcel.writeParcelable(teacher)
    }

    companion object CREATOR: Parcelable.Creator<LessonJson> {
        override fun createFromParcel(parcel: Parcel?) =
            LessonJson(parcel!!.readInt(),
            parcel.readString()!!,
            LessonTimeInterval(parcel.readInt(), parcel.readInt()),
            LessonTimeInterval(parcel.readInt(), parcel.readInt()),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            )

        override fun newArray(p0: Int) = arrayOfNulls<LessonJson>(p0)
    }
}