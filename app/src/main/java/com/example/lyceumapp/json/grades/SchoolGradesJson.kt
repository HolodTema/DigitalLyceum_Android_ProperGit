package com.example.lyceumapp.json.grades

import com.squareup.moshi.Json

data class SchoolGradesJson(
    @Json(name = "school_classes") val schoolGrades: List<GradeJson>
)
