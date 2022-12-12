package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TodayScheduleForSubgroupService {
    @GET("api/subgroup/{subgroup_id}/lessons_to_show_today")
    fun getTodayScheduleForSubgroup(@Path("subgroup_id") subgroupId: Int): Call<SubgroupTodayScheduleJson>
}