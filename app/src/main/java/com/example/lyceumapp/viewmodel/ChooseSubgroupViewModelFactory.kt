package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.json.grades.GradeJson

class ChooseSubgroupViewModelFactory(
    private val application: Application,
    private val chosenGrade: GradeJson):
ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseSubgroupViewModel(application, chosenGrade) as T
    }

}