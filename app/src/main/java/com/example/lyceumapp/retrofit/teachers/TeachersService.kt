package com.example.lyceumapp.retrofit.teachers

import com.example.lyceumapp.json.teachers.TeachersListJson
import retrofit2.Call
import retrofit2.http.GET

interface TeachersService {
    @GET("api/teachers")
    fun getTeachers(): Call<TeachersListJson>
}