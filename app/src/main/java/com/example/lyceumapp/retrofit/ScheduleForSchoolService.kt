package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.lessons.ScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleForSchoolService {
    @GET("/school/{school_id}/lesson")
    fun getScheduleForSchool(@Path("school_id") schoolId: Int): Call<ScheduleJson>
}