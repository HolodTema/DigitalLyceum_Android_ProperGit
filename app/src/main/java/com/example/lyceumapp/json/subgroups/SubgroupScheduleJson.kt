package com.example.lyceumapp.json.subgroups

import com.example.lyceumapp.json.lessons.LessonJson
import com.squareup.moshi.Json

data class SubgroupScheduleJson(
    @Json(name = "subgroup_id") val subgroupId: Int,
    @Json(name = "lessons") val lessons: List<LessonJson>
) {}