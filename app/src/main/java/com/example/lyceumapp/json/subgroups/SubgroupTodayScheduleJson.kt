package com.example.lyceumapp.json.subgroups

import com.example.lyceumapp.json.lessons.LessonJson
import com.squareup.moshi.Json

data class SubgroupTodayScheduleJson(
    @Json(name = "subgroup_id") val subgroupId: Int,
    @Json(name = "lessons") val lessons: List<LessonJson>,
    @Json(name = "weekday") val weekday: Int,
    @Json(name = "week") val week: Int
)
