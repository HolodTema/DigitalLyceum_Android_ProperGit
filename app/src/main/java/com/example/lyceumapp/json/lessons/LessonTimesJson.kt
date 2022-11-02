package com.example.lyceumapp.json.lessons

import com.squareup.moshi.Json
import com.example.lyceumapp.RequestManager.DaysOfWeek.*

data class LessonTimesJson(
    @Json(name = "school_class_id") val schoolClassId: Int,
    @Json(name = "Monday") val monday: List<List<Int>>?,
    @Json(name = "Tuesday") val tuesday: List<List<Int>>?,
    @Json(name = "Wednesday") val wednesday: List<List<Int>>?,
    @Json(name = "Thursday") val thursday: List<List<Int>>?,
    @Json(name = "Friday") val friday: List<List<Int>>?,
    @Json(name = "Saturday") val saturday: List<List<Int>>?
) {
    fun getDaysTimesArrayWithDayOfWeek() = arrayOf(monday to Monday, tuesday to Tuesday, wednesday to Wednesday, thursday to Thursday, friday to Friday, saturday to Saturday)


}