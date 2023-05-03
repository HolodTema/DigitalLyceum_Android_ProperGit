package com.example.lyceumapp.retrofit.schools

import com.example.lyceumapp.json.schools.SchoolsListJson
import retrofit2.Call
import retrofit2.http.GET

interface SchoolsService {
    @GET("api/schools")
    fun getSchools(): Call<SchoolsListJson>
}