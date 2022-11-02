package com.example.lyceumapp.json.schools

import com.squareup.moshi.Json

data class SchoolsListJson(
    @Json(name = "schools") val schools: List<SchoolJson>
)
