package com.example.lyceumapp.json.lessons

import com.squareup.moshi.Json

data class LessonJson(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "required") val required: Boolean,
    @Json(name = "teacher") val teacher: String,
    @Json(name = "times") val times: List<LessonTimesJson>
) {

}