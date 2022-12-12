package com.example.lyceumapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.*
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson
import com.example.lyceumapp.json.teachers.TeacherJson

class MainMenuViewModel(application: Application):
AndroidViewModel(application){
    private var timerNextLesson: CountDownTimer? = null

    //there are all lessons for subgroup
    val liveDataLessonsForSubgroup = MutableLiveData<Pair<List<LessonJson>?, Boolean>?>()
    //we use it in MainFragment to show schedule for today
    val liveDataTodaySchedule = MutableLiveData<SubgroupTodayScheduleJson?>()
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

    //after server update we don't need to use lots of fields, we will operate with SchoolJson, GradeJson objects etc
    private val schoolId: Int
    private val gradeId: Int
    private val subgroupId: Int
    private val schoolAddress: String?
    private val schoolName: String?

    init{
        //here we need to get all saved data from shPreferences. And we except, that shPreferences contain it
        val shPreferences = application.getSharedPreferences(Const.SH_PREFERENCES_NAME, Application.MODE_PRIVATE)
        schoolId = shPreferences.getInt(Const.SH_PREFERENCES_KEY_SCHOOL_ID, -1)
        gradeId = shPreferences.getInt(Const.SH_PREFERENCES_KEY_GRADE_ID, -1)
        subgroupId = shPreferences.getInt(Const.SH_PREFERENCES_KEY_SUBGROUP_ID, -1)
        schoolAddress = shPreferences.getString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, null)
        schoolName = shPreferences.getString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, null)
        if(schoolId==-1 || gradeId==-1 || subgroupId==-1 || schoolAddress==null || schoolName==null) {
            throw NoDataInShPreferencesException()
        }
        else {
            //here everything is alright and we can download schedule for the subgroup
            RequestManager.getScheduleForSubgroup(application.applicationContext, subgroupId) { lessons, isActual ->
                if(lessons==null) liveDataLessonsForSubgroup.value = null
                else {
                    RequestManager.getTodaySchedule(subgroupId) { todaySchedule ->
                        RequestManager.getTeachers() { teachers ->
                            liveDataLessonsForSubgroup.value = lessons to isActual
                            liveDataTodaySchedule.value = todaySchedule
                            liveDataTeachers.value = teachers

                            val nextLessonAndTimeToIt = RequestManager.getNextLessonAndTimeToIt(lessons)
                            liveDataNextLessonAndTimeToIt.value = nextLessonAndTimeToIt
                            if(nextLessonAndTimeToIt!=null) startNextLessonTimer(nextLessonAndTimeToIt.second.mills, nextLessonAndTimeToIt.first, nextLessonAndTimeToIt.second)
                        }
                    }
                }
            }
            
        }
    }

    fun getLessonsForDefiniteWeek(week: Int): List<LessonJson> {
        //our architecture can work only if this method is called from fragments of MainMenuActivity, when all lesson got and they're not null
        return RequestManager.getLessonsForDefiniteWeek(liveDataLessonsForSubgroup.value!!.first!!, week)
    }

    //this method returns array with default weekNames: 'week 1', 'week 2' etc
    fun getWeekNamesForSubgroup(): Array<String> {
        val amountWeeks = RequestManager.getAmountWeeksForSubgroup(liveDataLessonsForSubgroup.value!!.first!!)
        val result = Array(amountWeeks){""}
        val strWeek = getApplication<Application>().resources.getString(R.string.week)
        for(i in 0 until amountWeeks) {
            result[i] = "$strWeek ${i+1}"
        }
        return result
    }

    //this method allows to jump between fragments in navView
    fun updateChosenNavViewItemId(@IdRes itemId: Int) {
        liveDataChosenNavViewItemId.value = itemId
    }

    //we use this method in ScheduleFragment when the tab of tabLayout was changed and we need to get schedule for another day of week
    fun updateLessonsForDefiniteDay(lessons: List<LessonJson>, day: Int) {
        // TODO: we don't know what the week is here, so we use default week = 0
        liveDataLessonsForDefiniteDay.value = RequestManager.getLessonsForDefiniteDay(lessons, 0, day)
    }

    private fun startNextLessonTimer(beginMills: Long, lesson: LessonJson, deltaTime: RequestManager.DeltaTime) {
        timerNextLesson = object: CountDownTimer(beginMills, 1000*60){
            override fun onTick(p0: Long) {
                deltaTime.subtractMinute()
                liveDataNextLessonAndTimeToIt.value = lesson to deltaTime
            }

            override fun onFinish() {
                val nextLessonAndTimeToIt = RequestManager.getNextLessonAndTimeToIt(liveDataLessonsForSubgroup.value!!.first!!)
                liveDataNextLessonAndTimeToIt.value = nextLessonAndTimeToIt
                if(nextLessonAndTimeToIt!=null) startNextLessonTimer(nextLessonAndTimeToIt.second.mills, nextLessonAndTimeToIt.first, nextLessonAndTimeToIt.second)
            }
        }.start()
    }

    override fun onCleared() {
        timerNextLesson?.cancel()
    }
}