package com.example.lyceumapp.retrofit.lessons

import com.example.lyceumapp.json.lessons.ScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DayScheduleForSubgroupService {
    @GET("api/lessons")
    fun getDayScheduleForSubgroup(@Query("subgroup_id") subgroupId: Int,
                                  @Query("class_id") gradeId: Int,
                                  @Query("weekday") weekday: Int,
                                  @Query("do_double") doDouble: Boolean): Call<ScheduleJson>
}