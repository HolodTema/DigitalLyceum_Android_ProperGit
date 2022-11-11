package com.example.lyceumapp.retrofit

import com.example.lyceumapp.CantCreateRetrofitRequestException
import com.example.lyceumapp.Const
import com.example.lyceumapp.json.grades.SchoolGradesJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.json.schools.SchoolsListJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private const val BASE_URL = "https://api.lava-land.ru"


    private var retrofit: Retrofit? = null

    private fun createClient() {
        if(retrofit==null) {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(Const.NETWORK_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(Const.NETWORK_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(Const.NETWORK_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()))
                .build()
        }
    }

    fun getScheduleForGrade(gradeId: Int, listener: (ScheduleJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(ScheduleForGradeService::class.java)
        val call = service?.getScheduleForGrade(gradeId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<ScheduleJson>{
            override fun onResponse(call: Call<ScheduleJson>, response: Response<ScheduleJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<ScheduleJson>, t: Throwable) {
                listener(null)
            }
        })
    }

    fun getGradesForSchool(schoolId: Int, listener: (SchoolGradesJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(GradesForSchoolService::class.java)
        val call = service?.getGradesForSchool(schoolId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<SchoolGradesJson>{
            override fun onResponse(call: Call<SchoolGradesJson>, response: Response<SchoolGradesJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<SchoolGradesJson>, t: Throwable) {
                listener(null)
            }

        })
    }

    fun getScheduleForSchool(schoolId: Int, listener: (ScheduleJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(ScheduleForSchoolService::class.java)
        val call = service?.getScheduleForSchool(schoolId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<ScheduleJson> {
            override fun onResponse(call: Call<ScheduleJson>, response: Response<ScheduleJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<ScheduleJson>, t: Throwable) {
                listener(null)
            }
        })
    }

    fun getSchools(listener: (SchoolsListJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(SchoolsService::class.java)
        val call = service?.getSchools() ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<SchoolsListJson>{
            override fun onResponse(call: Call<SchoolsListJson>, response: Response<SchoolsListJson>) {
                listener(response.body())
            }
            override fun onFailure(call: Call<SchoolsListJson>, t: Throwable) {
                listener(null)
            }
        })
    }
}