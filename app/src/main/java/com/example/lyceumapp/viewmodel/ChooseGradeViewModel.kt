package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson

class ChooseGradeViewModel(application: Application, chosenSchool: SchoolJson): AndroidViewModel(application) {
    val liveDataListGrades = MutableLiveData<List<GradeJson>?>()
    //if even this attempt of downloading grades is the first (this case amountAttemptsToConnect from intent is always = null) , we still have default value = 1
    var amountAttemptsToConnect = 1
    lateinit var chosenGrade: GradeJson

    init{
        downloadGrades(chosenSchool.id)
    }

    private fun downloadGrades(schoolId: Int) {
        RequestManager.getGradesForSchool(schoolId) {
            if(it!=null && it.isNotEmpty()) chosenGrade = it[0]

            liveDataListGrades.value = it
        }
    }
}
