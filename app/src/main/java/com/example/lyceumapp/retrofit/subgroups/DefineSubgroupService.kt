package com.example.lyceumapp.retrofit.subgroups

import com.example.lyceumapp.json.subgroups.SubgroupJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DefineSubgroupService {
    @GET("api/subgroups/{subgroup_id}")
    fun getDefineSubgroup(@Path("subgroup_id") subgroupId: Int): Call<SubgroupJson>
}