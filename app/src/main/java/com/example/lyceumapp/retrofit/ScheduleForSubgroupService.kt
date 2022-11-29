package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.subgroups.SubgroupScheduleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleForSubgroupService {
    @GET("api/subgroup/{subgroup_id}/lesson")
    fun getScheduleForSubgroup(@Path("subgroup_id") subgroupId: Int): Call<SubgroupScheduleJson>
}