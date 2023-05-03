package com.example.lyceumapp.json.grades

import com.squareup.moshi.Json

data class SchoolGradesJson(
    @Json(name = "classes") val schoolGrades: List<GradeJson>
)
