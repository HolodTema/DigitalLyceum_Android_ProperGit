package com.example.lyceumapp.retrofit.grades

import com.example.lyceumapp.json.grades.SchoolGradesJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GradesForSchoolService {
    @GET("api/classes")
    fun getGradesForSchool(@Query("school_id") schoolId: Int): Call<SchoolGradesJson>
}