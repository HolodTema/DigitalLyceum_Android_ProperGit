package com.example.lyceumapp.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.lyceumapp.RequestManager

@Entity
data class LessonDB(
    @PrimaryKey(autoGenerate = true) val idInDatabase: Long,
    val schoolGradeId: Int,
    val name: String,
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,
    val teacher: String,
    val required: Boolean,
    val daysOfWeek: Int,
): Parcelable, Comparable<LessonDB> {

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeLong(idInDatabase)
        parcel?.writeInt(schoolGradeId)
        parcel?.writeString(name)
        parcel?.writeInt(startHour)
        parcel?.writeInt(startMinute)
        parcel?.writeInt(endHour)
        parcel?.writeInt(endMinute)
        parcel?.writeString(teacher)
        //actually parcel.writeBoolean() requires API level 29+, so we can use 0 like false and 1 like true
        val requiredInInt = when(required) {
            true -> 1
            else -> 0
        }
        parcel?.writeInt(requiredInInt)

        parcel?.writeInt(daysOfWeek)
    }

    companion object{
        @JvmField
        val CREATOR = object: Parcelable.Creator<LessonDB> {
            override fun createFromParcel(parcel: Parcel) = LessonDB(
                parcel.readLong(),
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString()!!,
                //actually parcel.writeBoolean() requires API level 29+, so we can use 0 like false and 1 like true
                parcel.readInt()==1,
                parcel.readInt()
            )

            override fun newArray(size: Int) = arrayOfNulls<LessonDB>(size)
        }

    }

    override fun compareTo(other: LessonDB): Int {
        return if(startHour>other.startHour) 1
        else if(startHour==other.startHour) {

            if(startMinute>other.startMinute) 1
            else if(startMinute==other.startMinute) 0
            else -1

        }
        else -1
    }

}