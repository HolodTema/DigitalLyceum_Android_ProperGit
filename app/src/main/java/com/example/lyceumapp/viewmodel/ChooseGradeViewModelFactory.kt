package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.json.schools.SchoolJson

class ChooseGradeViewModelFactory(private val application: Application, private val chosenSchool: SchoolJson):
ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseGradeViewModel(application, chosenSchool) as T
    }
}