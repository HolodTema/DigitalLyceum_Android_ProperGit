package com.example.lyceumapp.retrofit.lessons

import com.example.lyceumapp.json.lessons.ScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeekScheduleForSubgroupService {

    @GET("api/lessons")
    fun getWeekScheduleForSubgroup(@Query("subgroup_id") subgroupId: Int,
                                  @Query("class_id") gradeId: Int,
                                  @Query("do_double") doDouble: Boolean): Call<ScheduleJson>
}