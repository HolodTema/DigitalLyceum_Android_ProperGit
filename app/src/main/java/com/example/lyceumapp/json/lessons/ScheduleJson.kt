package com.example.lyceumapp.json.lessons

import com.squareup.moshi.Json

data class ScheduleJson(
    @Json(name = "lessons") val lessons: List<LessonJson>
)