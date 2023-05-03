package com.example.lyceumapp.retrofit.subgroups

import com.example.lyceumapp.json.subgroups.GradeSubgroupsJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface
SubgroupsForGradeService {
    @GET("api/subgroups")
    fun getSubgroupsForGrade(@Query("school_id") schoolId: Int, @Query("class_id") gradeId: Int): Call<GradeSubgroupsJson>
}