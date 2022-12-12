package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.subgroups.SubgroupInfoJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SubgroupInfoService {
    @GET("api/subgroup/{subgroup_id}")
    fun getSubgroupInfo(@Path("subgroup_id") subgroupId: Int): Call<SubgroupInfoJson>
}