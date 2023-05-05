package com.example.lyceumapp.retrofit

import com.example.lyceumapp.NETWORK_CONNECT_TIMEOUT_SECONDS
import com.example.lyceumapp.NETWORK_READ_TIMEOUT_SECONDS
import com.example.lyceumapp.NETWORK_WRITE_TIMEOUT_SECONDS
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.grades.SchoolGradesJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.schools.SchoolsListJson
import com.example.lyceumapp.json.subgroups.GradeSubgroupsJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.json.teachers.TeachersListJson
import com.example.lyceumapp.retrofit.grades.DefineGradeService
import com.example.lyceumapp.retrofit.grades.GradesForSchoolService
import com.example.lyceumapp.retrofit.lessons.DayScheduleForSubgroupService
import com.example.lyceumapp.retrofit.lessons.NearestDayScheduleForSubgroupService
import com.example.lyceumapp.retrofit.lessons.WeekScheduleForSubgroupService
import com.example.lyceumapp.retrofit.schools.DefineSchoolService
import com.example.lyceumapp.retrofit.schools.SchoolsService
import com.example.lyceumapp.retrofit.subgroups.DefineSubgroupService
import com.example.lyceumapp.retrofit.subgroups.SubgroupsForGradeService
import com.example.lyceumapp.retrofit.teachers.TeachersService
import com.example.lyceumapp.util.LoggingRetrofitCallback
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KProperty

object RetrofitManager {
    private const val BASE_URL = "https://dev.lyceumland.ru"


    private val retrofit: Retrofit by RetrofitDelegate()


    fun getTeachers(listener: (TeachersListJson?) -> Unit) {
        val service = retrofit.create(TeachersService::class.java)
        val call = service.getTeachers()
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
            listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getSchools(listener: (SchoolsListJson?) -> Unit) {
        val service = retrofit.create(SchoolsService::class.java)
        val call = service.getSchools()
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getDefineSchool(schoolId: Int, listener: (SchoolJson?) -> Unit) {
        val service = retrofit.create(DefineSchoolService::class.java)
        val call = service.getDefineSchool(schoolId)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getGradesForSchool(schoolId: Int, listener: (SchoolGradesJson?) -> Unit) {
        val service = retrofit.create(GradesForSchoolService::class.java)
        val call = service.getGradesForSchool(schoolId)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getDefineGrade(gradeId: Int, listener: (GradeJson?) -> Unit) {
        val service = retrofit.create(DefineGradeService::class.java)
        val call = service.getDefineGrade(gradeId)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getSubgroupsForGrade(schoolId: Int, gradeId: Int, listener: (GradeSubgroupsJson?) -> Unit) {
        val service = retrofit.create(SubgroupsForGradeService::class.java)
        val call = service.getSubgroupsForGrade(schoolId, gradeId)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getDefineSubgroup(subgroupId: Int, listener: (SubgroupJson?) -> Unit) {
        val service = retrofit.create(DefineSubgroupService::class.java)
        val call = service.getDefineSubgroup(subgroupId)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getWeekScheduleForSubgroup(subgroupId: Int, gradeId: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        val service = retrofit.create(WeekScheduleForSubgroupService::class.java)
        val call = service.getWeekScheduleForSubgroup(subgroupId, gradeId, doDouble)
        call.enqueue(LoggingRetrofitCallback({ _, response ->
            listener(response.body())
        },
        {
            listener(null)
        }))
    }

    fun getDayScheduleForSubgroup(subgroupId: Int, gradeId: Int, weekday: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        val service = retrofit.create(DayScheduleForSubgroupService::class.java)
        val call = service.getDayScheduleForSubgroup(subgroupId, gradeId, weekday, doDouble)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    fun getNearestDaySchedule(subgroupId: Int, gradeId: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        val service = retrofit.create(NearestDayScheduleForSubgroupService::class.java)
        val call = service.getNearestDayScheduleForSubgroup(subgroupId, gradeId, doDouble)
        call.enqueue(LoggingRetrofitCallback(
            { _, response ->
                listener(response.body())
            },
            {
                listener(null)
            }
        ))
    }

    class RetrofitDelegate {
        operator fun getValue(retrofitManager: RetrofitManager, property: KProperty<*>): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(NETWORK_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(NETWORK_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()))
                .build()
        }
    }
}