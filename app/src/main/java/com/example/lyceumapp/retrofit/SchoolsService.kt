package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.schools.SchoolsListJson
import retrofit2.Call
import retrofit2.http.GET

interface SchoolsService {
    @GET("api/school")
    fun getSchools(): Call<SchoolsListJson>
}