package com.example.lyceumapp

import android.content.Context
import com.example.lyceumapp.database.DatabaseClient
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.retrofit.RetrofitManager
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson
import com.example.lyceumapp.json.teachers.TeacherJson
import kotlinx.coroutines.*
import java.util.*

object RequestManager {

    fun getTeachers(listener: (List<TeacherJson>?) -> Unit) {
        RetrofitManager.getTeachers{
            if(it==null) listener(null)
            else listener(it.teachers)
        }
    }

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
        RetrofitManager.getScheduleForSubgroup(subgroupId) {
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

    fun getTodaySchedule(subgroupId: Int, listener: (SubgroupTodayScheduleJson?) -> Unit) {
        RetrofitManager.getTodaySchedule(subgroupId) {
            listener(it)
        }
    }

    // TODO: later Lawrence will create special request that returns amount of weeks for subgroup on the server
    fun getAmountWeeksForSubgroup(lessons: List<LessonJson>): Int {
        var maxWeek = 0
        for(lesson in lessons) {
            if(lesson.week>maxWeek) maxWeek = lesson.week
        }
        return maxWeek
    }

    //actually we don't know what week user has. And we can't just use todayLessons, because there can be Sunday or holidays
    //this method returns null if there is no lessons
    fun getNextLessonAndTimeToIt(lessons: List<LessonJson>): Pair<LessonJson, DeltaTime>? {
        fun getMinutesBetweenLessonAndCurrentTime(lesson: LessonJson, currentWeekday: Int, currentHour: Int, currentMinute: Int): Int {
            val minutesForLessonSinceBeginningOfWeek = lesson.weekday*24*60+lesson.startHour*60+lesson.startMinute
            val minutesForCurrentTimeSinceBeginningOfWeek = currentWeekday*24*60+currentHour*60+currentMinute
            return if(minutesForCurrentTimeSinceBeginningOfWeek > minutesForLessonSinceBeginningOfWeek) {
                (7*24*60-minutesForCurrentTimeSinceBeginningOfWeek)+minutesForLessonSinceBeginningOfWeek
            }
            else minutesForLessonSinceBeginningOfWeek-minutesForCurrentTimeSinceBeginningOfWeek
        }

        return if(lessons.isEmpty()) null
        else {
            val calendar = Calendar.getInstance()
            val currentWeekday = calendar.get(Calendar.DAY_OF_WEEK)-1
            val currentHour = calendar.get(Calendar.HOUR)
            val currentMinute = calendar.get(Calendar.MINUTE)

            var nextLesson = lessons[0] //we can't put null to it, because I don't want to deal with NullSave kotlin stuff... So, I init this var like lessons[0]
            var minTime = 24*7*60+1 //this field must be bigger than every int from fun above. We need it to our program works correctly
            var time = 0
            lessons.forEach{ lesson ->
                time = getMinutesBetweenLessonAndCurrentTime(lesson, currentWeekday, currentHour, currentMinute)
                if(time<minTime) {
                    minTime = time
                    nextLesson = lesson
                }
            }
            val days = minTime/(60*24)
            val hours = (minTime-days*60*24)/60
            val minutes = minTime-days*60*24-hours*60
            nextLesson to DeltaTime(days, hours, minutes)
        }

    }

    //actually now in design there's no function how to choose week. But we have this method in ScheduleFragment, where we set week = 0 as default
    // TODO: remove this comment if there's week choosing engine in the design
    fun getLessonsForDefiniteWeek(lessons: List<LessonJson>, week: Int): List<LessonJson> {
        val result = arrayListOf<LessonJson>()
        for(lesson in lessons) {
            if(lesson.week==week) result.add(lesson)
        }
        return result
    }

    //we use this method in ScheduleFragment, when the certain tab in tabLayout was chosen and we need to show a schedule for a day in viewPager
    fun getLessonsForDefiniteDay(lessons: List<LessonJson>, week: Int, day: Int): List<LessonJson> {
        val dayInServerFormat = when(day) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY ->3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> throw IncorrectDayOfWeekFormatException()
        }
        val result = arrayListOf<LessonJson>()
        for(lesson in lessons) {
            if(lesson.week==week && lesson.weekday==dayInServerFormat) result.add(lesson)
        }
        return result
    }

    fun day0to6toCalendarFormat(day0to6: Int): Int {
        return when(day0to6) {
            0 -> Calendar.MONDAY
            1 -> Calendar.TUESDAY
            2 -> Calendar.WEDNESDAY
            3 -> Calendar.THURSDAY
            4 -> Calendar.FRIDAY
            5 -> Calendar.SATURDAY
            6 -> Calendar.SUNDAY
            else -> throw IncorrectDayOfWeekFormatException()
        }
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

    data class DeltaTime(var days: Int, var hours: Int, var minutes: Int) {
        var mills: Long = ((days * 24 * 60 + hours * 60 + minutes) * 60_000).toLong()

        fun subtractMinute() {
            mills-=60_000
            if(minutes>0) minutes--
            else {
                if(hours>0) {
                    minutes = 59
                    hours--
                }
                else if(days>0) {
                    hours = 23
                    minutes = 59
                    days--
                }
            }
        }
    }
}