package com.example.lyceumapp

import android.content.Context
import com.example.lyceumapp.database.DatabaseClient
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.retrofit.RetrofitManager
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
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

    fun getDefineSchool(schoolId: Int, listener: (SchoolJson?) -> Unit) {
        RetrofitManager.getDefineSchool(schoolId) {
            listener(it)
        }
    }

    fun getGradesForSchool(schoolId: Int, listener: (List<GradeJson>?) -> Unit) {
        RetrofitManager.getGradesForSchool(schoolId) {
            listener(it?.schoolGrades)
        }
    }

    fun getDefineGrade(gradeId: Int, listener: (GradeJson?) -> Unit) {
        RetrofitManager.getDefineGrade(gradeId) {
            listener(it)
        }
    }

    fun getSubgroupsForGrade(schoolId: Int, gradeId: Int, listener: (List<SubgroupJson>?) -> Unit) {
        RetrofitManager.getSubgroupsForGrade(schoolId, gradeId) {
            listener(it?.subgroups)
        }
    }

    fun getDefineSubgroup(subgroupId: Int, listener: (SubgroupJson?) -> Unit) {
        RetrofitManager.getDefineSubgroup(subgroupId) {
            listener(it)
        }
    }

    fun getWeekScheduleForSubgroup(subgroupId: Int, gradeId: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        RetrofitManager.getWeekScheduleForSubgroup(subgroupId, gradeId, doDouble) {
            listener(it)
        }
    }

    fun getDayScheduleForSubgroup(subgroupId: Int, gradeId: Int, weekday: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        RetrofitManager.getDayScheduleForSubgroup(subgroupId, gradeId, weekday, doDouble) {
            listener(it)
        }
    }

    fun getNearestDayScheduleForSubgroup(subgroupId: Int, gradeId: Int, doDouble: Boolean = false, listener: (ScheduleJson?) -> Unit) {
        RetrofitManager.getNearestDaySchedule(subgroupId, gradeId, doDouble) {
            listener(it)
        }
    }

    //this method returns null if the next lesson is in another week, not in current weekType's week
    fun getNextLessonAndTimeToIt(lessons: List<LessonJson>, week: Boolean): Pair<LessonJson, DeltaTime>? {
        fun getMinutesBetweenLessonAndCurrentTime(lesson: LessonJson, currentWeekday: Int, currentHour: Int, currentMinute: Int): Int {
            val minutesForLessonSinceBeginningOfWeek = lesson.weekday*24*60+lesson.startTime.hour*60+lesson.startTime.minute
            val minutesForCurrentTimeSinceBeginningOfWeek = currentWeekday*24*60+currentHour*60+currentMinute
            return if(minutesForCurrentTimeSinceBeginningOfWeek > minutesForLessonSinceBeginningOfWeek) {
                (7*24*60-minutesForCurrentTimeSinceBeginningOfWeek)+minutesForLessonSinceBeginningOfWeek
            }
            else minutesForLessonSinceBeginningOfWeek-minutesForCurrentTimeSinceBeginningOfWeek
        }
        val lessonsForCurrentWeek = getLessonsForDefiniteWeek(lessons, week)

        return if(lessonsForCurrentWeek.isEmpty()) null
        else {
            val calendar = Calendar.getInstance()
            val currentWeekday = calendar.get(Calendar.DAY_OF_WEEK)-1
            val currentHour = calendar.get(Calendar.HOUR)
            val currentMinute = calendar.get(Calendar.MINUTE)

            var nextLesson = lessonsForCurrentWeek[0] //we can't put null to it, because I don't want to deal with NullSave kotlin stuff... So, I init this var like lessons[0]
            var minTime = 24*7*60+1 //this field must be bigger than every int from fun above. We need it to our program works correctly
            var time = 0
            lessonsForCurrentWeek.forEach{ lesson ->
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

    fun getLessonsForDefiniteWeek(lessons: List<LessonJson>, week: Boolean): List<LessonJson> {
        val result = arrayListOf<LessonJson>()
        for(lesson in lessons) {
            if(lesson.week==week) result.add(lesson)
        }
        return result
    }

    //we use this method in ScheduleFragment, when the certain tab in tabLayout was chosen and we need to show a schedule for a day in viewPager
    fun getLessonsForDefiniteDay(lessons: List<LessonJson>, weekday: Int, listener: (List<LessonJson>) -> Unit): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                val result = arrayListOf<LessonJson>()
                for(lesson in lessons) {
                    if(lesson.weekday==weekday) result.add(lesson)
                }
                delay(100L)
                return@async result
            }
            listener(deferred.await())
        }

    }

    fun getArrayOfDayLessons(lessons: List<LessonJson>, listener: (Array<MutableList<LessonJson>>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                val result = Array<MutableList<LessonJson>>(7) { mutableListOf() }
                for(lesson in lessons) {
                    result[lesson.weekday].add(lesson)
                }
                return@async result
            }
            listener(deferred.await())
        }
    }

    fun checkIfLessonsToday(weekLessons: List<LessonJson>, weekday: Int, listener: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                for(lesson in weekLessons) {
                    if(lesson.weekday==weekday) {
                        return@async true
                    }
                }
                return@async false
            }
            listener(deferred.await())
        }
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

    fun dayCalendarFormatTo0to6(dayCalendarFormat: Int): Int {
        return when(dayCalendarFormat) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY ->3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
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

//        val deferred = CoroutineScope(Dispatchers.IO).async {
//
//        }
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