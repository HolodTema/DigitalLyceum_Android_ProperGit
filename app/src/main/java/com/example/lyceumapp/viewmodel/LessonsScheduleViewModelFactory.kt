package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.json.lessons.LessonJson

class LessonsScheduleViewModelFactory(private val application: Application,
private val lessonsForSubgroup: ArrayList<LessonJson>):
ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LessonsScheduleViewModel(application, lessonsForSubgroup) as T
    }
}