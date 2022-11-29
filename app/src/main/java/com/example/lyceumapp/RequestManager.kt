package com.example.lyceumapp

import android.content.Context
import com.example.lyceumapp.database.DatabaseClient
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.retrofit.RetrofitManager
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

object RequestManager {
    //we need this field because we don't need to make request to server EVERY time we launch MainMenuActivity. After first time we just get LessonDB from database.
    var isRequestScheduleForGradeAlreadyGot = false
    private set

    var isRequestScheduleForSubgroupAlreadyGot = false
    private set

    fun getSchools(listener: (List<SchoolJson>?) -> Unit) {
        RetrofitManager.getSchools{
            if(it!=null) listener(it.schools.sorted())
            else listener(null)
        }
    }

    fun getGradesForSchool(schoolId: Int, listener: (List<GradeJson>?) -> Unit) {
        RetrofitManager.getGradesForSchool(schoolId) {
            listener(it?.schoolGrades)
        }
    }

    fun getSubgroupsForGrade(gradeId: Int, listener: (List<SubgroupJson>?) -> Unit) {
        RetrofitManager.getSubgroupsForGrade(gradeId) {
            listener(it?.subgroups)
        }
    }


    //here the listener param of function, that contains also boolean field. That field implies "isLessonsActual" -
    //so, it returns true if we downloaded lessons from the server recently. And it returns false in case when the server is unable to connect
    fun getScheduleForSubgroup(context: Context, subgroupId: Int, listener: (List<LessonJson>?, Boolean) -> Unit) {
        //we don't need to create request to the server every time we launch MainMenuActivity. We need to do it only
        //one time during the app lifecycle
        if(isRequestScheduleForSubgroupAlreadyGot) {
            //here we've already made request to the server and we don't have to do it again. We can get lessons from
            //a local database
            getLessonsFromLocalDatabase(context) { lessonsFromLocalDatabase ->
                listener(lessonsFromLocalDatabase, true)
            }
        }
        else RetrofitManager.getScheduleForSubgroup(subgroupId) {
            if(it==null) {
                //something went wrong, for example there is no Internet. We need to check local database
                CoroutineScope(Dispatchers.Main).launch {
                    val deferredAreLessonsInLocalDatabase = async(Dispatchers.IO) {
                        DatabaseClient.getInstance(context).lessonDao().areLessonsInDatabase()
                    }

                    if(deferredAreLessonsInLocalDatabase.await()) {
                        //there are lessons in database. we need to get it
                        getLessonsFromLocalDatabase(context) { lessonsFromLocalDatabase ->
                            listener(lessonsFromLocalDatabase, false)
                        }
                    }
                    else {
                        //actually there are no lessons in database even. It's no good to do something else...
                        //just pass the null
                        listener(null, false)
                    }

                }
            }
            else {
                //everything is correct, we need to return the list of lessonJson objects to the listener
                //and we need to cache lessons to the Room database here
                cacheLessonsToLocalDatabase(context, it.lessons) {
                    listener(it.lessons, true)
                }

            }
        }
    }

    fun getLessonsForOneDayOfSubgroup(lessons: ArrayList<LessonJson>, dayOfWeek: Int): ArrayList<LessonJson> {
        val result = ArrayList<LessonJson>()
        for( lesson in lessons) {
            if(lesson.weekday==dayOfWeek) result.add(lesson)
        }
        result.sort()
        return result
    }

    // TODO: later Lawrence will create special request that returns amount of weeks for subgroup on the server
    fun getAmountWeeksForSubgroup(lessons: ArrayList<LessonJson>): Int {
        var maxWeek = 0
        for(lesson in lessons) {
            if(lesson.week>maxWeek) maxWeek = lesson.week
        }
        return maxWeek
    }

//    fun getNextLessonAndTimeToIt(lessons: List<LessonJson>): Pair<LessonJson, Int> {
//        val calendar = Calendar.getInstance()
//        for(lesson in lessons) {
//
//        }
//    }

    fun getLessonsForDefiniteWeek(lessons: ArrayList<LessonJson>, week: Int): ArrayList<LessonJson> {
        val result = arrayListOf<LessonJson>()
        for(lesson in lessons) {
            if(lesson.week==week) result.add(lesson)
        }
        return result
    }

    private fun cacheLessonsToLocalDatabase(context: Context, lessons: List<LessonJson>, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferredInsertLessonsIntoDatabase = async(Dispatchers.IO) {
                val lessonDao = DatabaseClient.getInstance(context).lessonDao()

                lessonDao.deleteAll()
                for(lesson in lessons) {
                    lesson.id = 0
                    lessonDao.insert(lesson)
                }
            }
            deferredInsertLessonsIntoDatabase.await()
            listener()
        }
    }

    private fun getLessonsFromLocalDatabase(context: Context, listener: (List<LessonJson>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferredLessonsFromLocalDatabase = async(Dispatchers.IO) {
                DatabaseClient.getInstance(context).lessonDao().getAll()
            }
            listener(deferredLessonsFromLocalDatabase.await())
        }
    }
}