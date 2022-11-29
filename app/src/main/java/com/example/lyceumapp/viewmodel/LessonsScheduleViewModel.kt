package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lyceumapp.R
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.lessons.LessonJson

class LessonsScheduleViewModel(application: Application,
val lessonsForSubgroup: ArrayList<LessonJson>):
AndroidViewModel(application) {
    val weekNames: ArrayList<String>

    var lessonsForDefiniteWeek: ArrayList<LessonJson>
    private set

    var chosenWeek = 0

    init{
        val amountWeeks = RequestManager.getAmountWeeksForSubgroup(lessonsForSubgroup)
        weekNames = ArrayList(amountWeeks)
        for(i in 1..amountWeeks) {
            weekNames.add(String.format(application.resources.getString(R.string.week), i))
        }

        lessonsForDefiniteWeek = RequestManager.getLessonsForDefiniteWeek(lessonsForSubgroup, chosenWeek)
    }

    fun updateLessonsForDefiniteWeek() {
        lessonsForDefiniteWeek = RequestManager.getLessonsForDefiniteWeek(lessonsForSubgroup, chosenWeek)
    }

}