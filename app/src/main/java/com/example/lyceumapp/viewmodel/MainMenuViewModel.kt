package com.example.lyceumapp.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.Const
import com.example.lyceumapp.NoDataInShPreferencesException
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.lessons.LessonJson

class MainMenuViewModel(application: Application):
AndroidViewModel(application){
    val liveDataLessonsForSubgroup = MutableLiveData<Pair<List<LessonJson>?, Boolean>>()
    var amountAttemptsToConnect = 1

    private val schoolId: Int
    private val gradeId: Int
    private val subgroupId: Int
    val schoolAddress: String?
    val schoolName: String?

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
                liveDataLessonsForSubgroup.value = lessons to isActual
            }
        }
    }



}