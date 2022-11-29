package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.grades.SchoolGradesJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GradesForSchoolService {
    @GET("api/school/{school_id}/class")
    fun getGradesForSchool(@Path("school_id") schoolId: Int): Call<SchoolGradesJson>
}