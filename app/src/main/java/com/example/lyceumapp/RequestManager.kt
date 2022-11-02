package com.example.lyceumapp

import android.content.Context
import com.example.lyceumapp.database.DatabaseClient
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.grades.SchoolGradesJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.schools.SchoolsListJson
import com.example.lyceumapp.retrofit.RetrofitManager
import com.example.lyceumapp.database.LessonDB
import com.example.lyceumapp.json.lessons.LessonJson
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

object RequestManager {
    //we need this field because we don't need to make request to server EVERY time we launch MainMenuActivity. After first time we just get LessonDB from database.
    var isRequestScheduleForGradeAlreadyGot = false
    private set

    fun getGradesForSchool(schoolId: Int, listener: OnResponseGotListener<List<GradeJson>?>) {
        RetrofitManager.getGradesForSchool(schoolId, object: RetrofitManager.OnRetrofitResponseGotListener<SchoolGradesJson?> {
            override fun onRetrofitResponseGot(obj: SchoolGradesJson?) {
                listener.onResponseGot(obj?.schoolGrades)
            }

        })
    }

    fun getSchools(listener: OnResponseGotListener<List<SchoolJson>?>) {
        RetrofitManager.getSchools(object: RetrofitManager.OnRetrofitResponseGotListener<SchoolsListJson?>{
            override fun onRetrofitResponseGot(obj: SchoolsListJson?) {
                listener.onResponseGot(obj?.schools)
            }
        })
    }

    fun getScheduleForGrade(context: Context, gradeId: Int, listener: OnResponseGotListener<ArrayList<LessonDB>?>) {
        if(isRequestScheduleForGradeAlreadyGot) {
            CoroutineScope(Dispatchers.Main).launch {
                val deferred = CoroutineScope(Dispatchers.IO).async {
                    DatabaseClient.getInstance(context).lessonDao().getBySchoolGradeId(gradeId)
                }
                listener.onResponseGot(deferred.await() as ArrayList<LessonDB>)
            }
        }
        else RetrofitManager.getScheduleForGrade(gradeId, object: RetrofitManager.OnRetrofitResponseGotListener<ScheduleJson?> {
            override fun onRetrofitResponseGot(obj: ScheduleJson?) {
                CoroutineScope(Dispatchers.Main).launch {
                    if(obj==null) {
                        val deferredAreLessonsOfSchoolGradeIdInDb = CoroutineScope(Dispatchers.IO).async {
                            DatabaseClient.getInstance(context).lessonDao().areLessonsOfSchoolGradeIdInDatabase(gradeId)
                        }
                        val areLessonsOfSchoolGradeIdInDb = deferredAreLessonsOfSchoolGradeIdInDb.await()

                        if(areLessonsOfSchoolGradeIdInDb) {
                            val deferredGetLessonsByGradeId = CoroutineScope(Dispatchers.IO).async {
                                DatabaseClient.getInstance(context).lessonDao().getBySchoolGradeId(gradeId)
                            }
                            listener.onResponseGot(deferredGetLessonsByGradeId.await() as ArrayList<LessonDB> )
                        }
                        else {
                            listener.onResponseGot(null)
                        }
                    }
                    else {
                        val deferredGetConvertedLessonDBList = CoroutineScope(Dispatchers.IO).async {
                            val convertedLessons = convertLessonsJsonToSimpleFormat(obj.lessons)

                            if(DatabaseClient.getInstance(context).lessonDao().areLessonsOfSchoolGradeIdInDatabase(gradeId)) {
                                DatabaseClient.getInstance(context).lessonDao().getBySchoolGradeId(gradeId).forEach {
                                    DatabaseClient.getInstance(context).lessonDao().delete(it)
                                }
                            }

                            convertedLessons.forEach{
                                DatabaseClient.getInstance(context).lessonDao().insert(it)
                            }
                            convertedLessons
                        }
                        isRequestScheduleForGradeAlreadyGot = true
                        listener.onResponseGot(deferredGetConvertedLessonDBList.await())
                    }
                }
            }
        })
    }

    fun getLessonsForOneDayOfGrade(lessonsForGrade: ArrayList<LessonDB>, daysOfWeekOrdinal: Int): ArrayList<LessonDB> {
        val result = arrayListOf<LessonDB>()
        lessonsForGrade.forEach{
            if(it.daysOfWeek==daysOfWeekOrdinal) result.add(it)
        }
        result.sort()
        return result
    }

    fun getNextLessonAndTimeToIt(lessons: ArrayList<LessonDB>): Pair<LessonDB, Pair<Int, Int>> {
        fun getMinutesBetweenLessonAndCurrentTime(lesson: LessonDB, currentDayOrdinal: Int, currentHour: Int, currentMinute: Int): Int {
            val minutesForLessonSinceBeginningOfWeek = lesson.daysOfWeek*24*60+lesson.startHour*60+lesson.startMinute
            val minutesForCurrentTimeSinceBeginningOfWeek = currentDayOrdinal*24*60+currentHour*60+currentMinute
            return if(minutesForCurrentTimeSinceBeginningOfWeek>minutesForLessonSinceBeginningOfWeek) {
                7*24*60-(minutesForCurrentTimeSinceBeginningOfWeek-minutesForLessonSinceBeginningOfWeek)
            }
            else minutesForLessonSinceBeginningOfWeek-minutesForCurrentTimeSinceBeginningOfWeek
        }

        val calendar = Calendar.getInstance()
        val currentDayOrdinal = calendar.get(Calendar.DAY_OF_WEEK)
        val currentHour = calendar.get(Calendar.HOUR)
        val currentMinute = calendar.get(Calendar.MINUTE)

        var nextLesson = lessons[0] //we can't put null to it, because I don't want to deal with NullSave kotlin stuff... So, I init this var like lessons[0]
        var minTime = 24*7*60+1 //this field must be bigger than every int from fun above. We need it to our program works correctly
        var time = 0
        lessons.forEach{ lesson ->
            time = getMinutesBetweenLessonAndCurrentTime(lesson, currentDayOrdinal, currentHour, currentMinute)
            if(time<minTime) {
                minTime = time
                nextLesson = lesson
            }
        }
        return nextLesson to (minTime/60 to minTime % 60)
    }

    private fun convertLessonsJsonToSimpleFormat(lessonForGradeJsonList: List<LessonJson>): ArrayList<LessonDB> {
        val result = arrayListOf<LessonDB>()

        lessonForGradeJsonList.forEach { lessonJson ->
            lessonJson.times.forEach { lessonTimesJson ->
                lessonTimesJson.getDaysTimesArrayWithDayOfWeek().forEach { timesDayListWithDayOfWeek ->
                    timesDayListWithDayOfWeek.first?.forEach { timesForOneCertainLesson ->
                        result.add(
                            LessonDB(
                                0,
                                lessonTimesJson.schoolClassId,
                                lessonJson.name,
                                timesForOneCertainLesson[0],
                                timesForOneCertainLesson[1],
                                timesForOneCertainLesson[2],
                                timesForOneCertainLesson[3],
                                lessonJson.teacher,
                                lessonJson.required,
                                timesDayListWithDayOfWeek.second.ordinal
                            )
                        )
                    }
                }
            }
//            lessonForGradeJson.times.getDaysTimesArrayWithDayOfWeek().forEach { timesDayListWithDayOfWeek ->
//                timesDayListWithDayOfWeek.first?.forEach { timesForOneLesson ->
//                    result.add(
//                        LessonDB(
//                            0,
//                            lessonForGradeJson.name,
//                            timesForOneLesson[0],
//                            timesForOneLesson[1],
//                            timesForOneLesson[2],
//                            timesForOneLesson[3],
//                            lessonForGradeJson.teacher,
//                            lessonForGradeJson.required,
//                            timesDayListWithDayOfWeek.second.ordinal
//                        )
//                    )
//                }
//            }
        }

        return result
    }

    interface OnResponseGotListener<T> {
        fun onResponseGot(obj: T)
    }

    enum class DaysOfWeek{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday
    }
}