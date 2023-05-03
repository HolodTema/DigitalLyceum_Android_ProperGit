package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson

class MainMenuViewModelFactory(private val application: Application,
private val school: SchoolJson,
private val grade: GradeJson,
private val subgroup: SubgroupJson):
ViewModelProvider.AndroidViewModelFactory(application){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainMenuViewModel(application, school, grade, subgroup) as T
    }

}