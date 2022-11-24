package com.example.lyceumapp.json.grades

import com.squareup.moshi.Json

data class SchoolGradesJson(
    @Json(name="school_id") val schoolId: Int,
    @Json(name = "classes") val schoolGrades: List<GradeJson>
)
