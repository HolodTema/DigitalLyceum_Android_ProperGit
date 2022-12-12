package com.example.lyceumapp.retrofit

import android.util.Log
import com.example.lyceumapp.CantCreateRetrofitRequestException
import com.example.lyceumapp.Const
import com.example.lyceumapp.json.grades.SchoolGradesJson
import com.example.lyceumapp.json.schools.SchoolsListJson
import com.example.lyceumapp.json.subgroups.GradeSubgroupsJson
import com.example.lyceumapp.json.subgroups.SubgroupInfoJson
import com.example.lyceumapp.json.subgroups.SubgroupScheduleJson
import com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson
import com.example.lyceumapp.json.teachers.TeachersListJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private const val BASE_URL = "https://dev.lava-land.ru"


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

    fun getTeachers(listener: (TeachersListJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(TeachersService::class.java)
        val call = service?.getTeachers() ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<TeachersListJson> {
            override fun onResponse(call: Call<TeachersListJson>, response: Response<TeachersListJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<TeachersListJson>, t: Throwable) {
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
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
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
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
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
                listener(null)
            }

        })
    }

    fun getSubgroupsForGrade(gradeId: Int, listener: (GradeSubgroupsJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(SubgroupsForGradeService::class.java)
        val call = service?.getSubgroupsForGrade(gradeId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<GradeSubgroupsJson> {
            override fun onResponse(call: Call<GradeSubgroupsJson>, response: Response<GradeSubgroupsJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<GradeSubgroupsJson>, t: Throwable) {
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
                listener(null)
            }
        })
    }

    fun getSubgroupInfo(subgroupId: Int, listener: (SubgroupInfoJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(SubgroupInfoService::class.java)
        val call = service?.getSubgroupInfo(subgroupId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<SubgroupInfoJson> {
            override fun onResponse(call: Call<SubgroupInfoJson>, response: Response<SubgroupInfoJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<SubgroupInfoJson>, t: Throwable) {
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
                listener(null)
            }
        })
    }

    fun getScheduleForSubgroup(subgroupId: Int, listener: (SubgroupScheduleJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(ScheduleForSubgroupService::class.java)
        val call = service?.getScheduleForSubgroup(subgroupId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<SubgroupScheduleJson> {
            override fun onResponse(call: Call<SubgroupScheduleJson>, response: Response<SubgroupScheduleJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<SubgroupScheduleJson>, t: Throwable) {
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
                listener(null)
            }
        })
    }

    fun getTodaySchedule(subgroupId: Int, listener: (SubgroupTodayScheduleJson?) -> Unit) {
        createClient()
        val service = retrofit?.create(TodayScheduleForSubgroupService::class.java)
        val call = service?.getTodayScheduleForSubgroup(subgroupId) ?: throw CantCreateRetrofitRequestException()
        call.enqueue(object: Callback<SubgroupTodayScheduleJson> {
            override fun onResponse(call: Call<SubgroupTodayScheduleJson>, response: Response<SubgroupTodayScheduleJson>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<SubgroupTodayScheduleJson>, t: Throwable) {
                Log.e(Const.LOG_TAG_RETROFIT_ON_FAILURE, t.toString())
                listener(null)
            }
        })
    }
}