package com.example.lyceumapp.viewmodel

import android.app.Application
import android.content.Intent
import android.os.CountDownTimer
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT
import com.example.lyceumapp.INTENT_KEY_GRADE
import com.example.lyceumapp.INTENT_KEY_NO_RESPONSE_TYPE
import com.example.lyceumapp.INTENT_KEY_SCHOOL
import com.example.lyceumapp.INTENT_KEY_SUBGROUP
import com.example.lyceumapp.NoResponseType
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.activity.NoResponseActivity
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import com.example.lyceumapp.json.teachers.TeacherJson
import kotlinx.coroutines.Job

class MainMenuViewModel(application: Application,
val school: SchoolJson,
val grade: GradeJson,
private val subgroup: SubgroupJson):
AndroidViewModel(application){
    private var timerNextLesson: CountDownTimer? = null

    private lateinit var arrayOfDayLessons: Array<MutableList<LessonJson>>

    //we use it in MainFragment to show the time before next lesson in real time with CountDownTimer...
    val liveDataNextLessonAndTimeToIt = MutableLiveData<Pair<LessonJson, RequestManager.DeltaTime>?>()
    //we need this liveData for simple jumping between fragments in navView for proper working
    val liveDataChosenNavViewItemId = MutableLiveData<Int>()
    //actually teachers haven't done on server by Lawrence yet, but I've already added it
    val liveDataTeachers = MutableLiveData<List<TeacherJson>?>()
    //classic field against ddos-attacks :)
    var amountAttemptsToConnect = 1

    val liveDataNearestDayLessons = MutableLiveData<ScheduleJson?>()
    val liveDataWeekLessons = MutableLiveData<List<LessonJson>>()

    init{
        RequestManager.getWeekScheduleForSubgroup(subgroup.id, grade.id) { weekSchedule ->
            if(weekSchedule==null) startNoResponseActivity()
            else {
                RequestManager.getTeachers { teachers ->
                    RequestManager.getNearestDayScheduleForSubgroup(subgroup.id, grade.id) {nearestSchedule ->
                        liveDataWeekLessons.value = weekSchedule.lessons
                        liveDataTeachers.value = teachers
                        liveDataNearestDayLessons.value = nearestSchedule
                    }

                }
            }
        }
    }

    //this method allows to jump between fragments in navView
    fun updateChosenNavViewItemId(@IdRes itemId: Int) {
        liveDataChosenNavViewItemId.value = itemId
    }

    fun updateArrayOfDayLessons(lessons: List<LessonJson>, listener: () -> Unit) {
        if(!this::arrayOfDayLessons.isInitialized) {
            RequestManager.getArrayOfDayLessons(lessons) {
                arrayOfDayLessons = it
                listener()
            }
        }
        else listener()
    }

    fun getDayLessons(weekday: Int): List<LessonJson> {
        return arrayOfDayLessons[weekday]
    }

    fun checkIfLessonsToday(weekLessons: List<LessonJson>, weekday: Int, listener: (Boolean) -> Unit) {
        RequestManager.checkIfLessonsToday(weekLessons, weekday) {
            listener(it)
        }
    }

    private fun startNextLessonTimer(beginMills: Long, lesson: LessonJson, deltaTime: RequestManager.DeltaTime) {
        timerNextLesson = object: CountDownTimer(beginMills, 1000*60){
            override fun onTick(p0: Long) {
                deltaTime.subtractMinute()
                liveDataNextLessonAndTimeToIt.value = lesson to deltaTime
            }

            override fun onFinish() {
                val nextLessonAndTimeToIt = RequestManager.getNextLessonAndTimeToIt(liveDataNearestDayLessons.value!!.lessons, true)
                liveDataNextLessonAndTimeToIt.value = nextLessonAndTimeToIt
                if(nextLessonAndTimeToIt!=null) startNextLessonTimer(nextLessonAndTimeToIt.second.mills, nextLessonAndTimeToIt.first, nextLessonAndTimeToIt.second)
            }
        }.start()
    }

    private fun startNoResponseActivity() {
        val intent = Intent(getApplication(), NoResponseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra(INTENT_KEY_NO_RESPONSE_TYPE, NoResponseType.GetLessons.name)
        intent.putExtra(INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, amountAttemptsToConnect)
        intent.putExtra(INTENT_KEY_SCHOOL, school)
        intent.putExtra(INTENT_KEY_GRADE, grade)
        intent.putExtra(INTENT_KEY_SUBGROUP, subgroup)
        getApplication<Application>().startActivity(intent)
    }


    override fun onCleared() {
        timerNextLesson?.cancel()
    }
}