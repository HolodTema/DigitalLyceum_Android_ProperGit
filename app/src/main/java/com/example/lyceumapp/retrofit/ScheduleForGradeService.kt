package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.lessons.ScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleForGradeService {
    @GET("/school_class/{class_id}/lesson")
    fun getScheduleForGrade(@Path("class_id") gradeId: Int): Call<ScheduleJson>
}