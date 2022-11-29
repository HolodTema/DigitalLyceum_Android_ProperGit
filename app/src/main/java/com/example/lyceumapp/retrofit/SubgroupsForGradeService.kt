package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.subgroups.GradeSubgroupsJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SubgroupsForGradeService {
    @GET("api/class/{class_id}/subgroup")
    fun getSubgroupsForGrade(@Path("class_id") gradeId: Int): Call<GradeSubgroupsJson>
}