package com.example.lyceumapp.retrofit

import com.example.lyceumapp.json.teachers.TeachersListJson
import retrofit2.Call
import retrofit2.http.GET

interface TeachersService {
    @GET("api/teacher")
    fun getTeachers(): Call<TeachersListJson>
}