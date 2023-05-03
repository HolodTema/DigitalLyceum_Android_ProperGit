package com.example.lyceumapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.*
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson
import com.example.lyceumapp.json.teachers.TeacherJson

class MainMenuViewModel(application: Application,
private val school: SchoolJson,
private val grade: GradeJson,
private val subgroup: SubgroupJson):
AndroidViewModel(application){
    private var timerNextLesson: CountDownTimer? = null

    //there are all lessons for subgroup
    val liveDataLessonsForSubgroup = MutableLiveData<Pair<List<LessonJson>?, Boolean>?>()
    //we use it in MainFragment to show schedule for today
    val liveDataTodaySchedule = MutableLiveData<List<LessonJson>?>()
    //we use it in MainFragment to show the time before next lesson in real time with CountDownTimer...
    val liveDataNextLessonAndTimeToIt = MutableLiveData<Pair<LessonJson, RequestManager.DeltaTime>?>()
    //we use it in LessonsScheduleFragment to show schedule for day in viewPager powered by tabLayout
    val liveDataLessonsForDefiniteDay = MutableLiveData<List<LessonJson>>()
    //we need this liveData for simple jumping between fragments in navView for proper working
    val liveDataChosenNavViewItemId = MutableLiveData<Int>()
    //actually teachers haven't done on server by Lawrence yet, but I've already added it
    val liveDataTeachers = MutableLiveData<List<TeacherJson>?>()
    //classic field against ddos-attacks :)
    var amountAttemptsToConnect = 1

    init{
        RequestManager.getScheduleForSubgroup(application.applicationContext, subgroup.id, grade.id) { lessons, isActual ->
            if (lessons == null) liveDataLessonsForSubgroup.value = null
            else {
                RequestManager.getTodaySchedule(subgroup.id, grade.id) { todayLessons ->
                    RequestManager.getTeachers() { teachers ->
                        liveDataLessonsForSubgroup.value = lessons to isActual
                        liveDataTodaySchedule.value = todayLessons
                        liveDataTeachers.value = teachers
// TODO: how can we know what week does user have? 
                        val nextLessonAndTimeToIt =
                            RequestManager.getNextLessonAndTimeToIt(lessons, true)
                        liveDataNextLessonAndTimeToIt.value = nextLessonAndTimeToIt
                        if (nextLessonAndTimeToIt != null) startNextLessonTimer(
                            nextLessonAndTimeToIt.second.mills,
                            nextLessonAndTimeToIt.first,
                            nextLessonAndTimeToIt.second
                        )
                    }
                }
            }
        }
    }

    fun getLessonsForDefiniteWeek(week: Boolean): List<LessonJson> {
        //our architecture can work only if this method is called from fragments of MainMenuActivity, when all lesson got and they're not null
        return RequestManager.getLessonsForDefiniteWeek(liveDataLessonsForSubgroup.value!!.first!!, week)
    }

    //this method allows to jump between fragments in navView
    fun updateChosenNavViewItemId(@IdRes itemId: Int) {
        liveDataChosenNavViewItemId.value = itemId
    }

    //we use this method in ScheduleFragment when the tab of tabLayout was changed and we need to get schedule for another day of week
    fun updateLessonsForDefiniteDay(lessons: List<LessonJson>, week: Boolean, day: Int) {
        liveDataLessonsForDefiniteDay.value = RequestManager.getLessonsForDefiniteDay(lessons, week, day)
    }

    private fun startNextLessonTimer(beginMills: Long, lesson: LessonJson, deltaTime: RequestManager.DeltaTime) {
        timerNextLesson = object: CountDownTimer(beginMills, 1000*60){
            override fun onTick(p0: Long) {
                deltaTime.subtractMinute()
                liveDataNextLessonAndTimeToIt.value = lesson to deltaTime
            }

            override fun onFinish() {
                val nextLessonAndTimeToIt = RequestManager.getNextLessonAndTimeToIt(liveDataLessonsForSubgroup.value!!.first!!, true)
                liveDataNextLessonAndTimeToIt.value = nextLessonAndTimeToIt
                if(nextLessonAndTimeToIt!=null) startNextLessonTimer(nextLessonAndTimeToIt.second.mills, nextLessonAndTimeToIt.first, nextLessonAndTimeToIt.second)
            }
        }.start()
    }

    override fun onCleared() {
        timerNextLesson?.cancel()
    }
}