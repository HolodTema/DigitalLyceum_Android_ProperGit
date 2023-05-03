package com.example.lyceumapp.retrofit.lessons

import com.example.lyceumapp.json.lessons.ScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NearestDayScheduleForSubgroupService {

    @GET("api/lessons/nearest_day")
    fun getNearestDayScheduleForSubgroup(@Query("subgroup_id") subgroupId: Int,
        @Query("class_id") classId: Int,
        @Query("do_double") doDouble: Boolean): Call<ScheduleJson>
}