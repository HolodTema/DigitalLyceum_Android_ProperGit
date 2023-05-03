package com.example.lyceumapp.retrofit.schools

import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DefineSchoolService {
    @GET("api/schools/{school_id}")
    fun getDefineSchool(@Path("school_id") schoolId: Int): Call<SchoolJson>
}