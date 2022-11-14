package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson

class ChooseGradeViewModel(application: Application, chosenSchool: SchoolJson): AndroidViewModel(application) {
    val liveDataListGrades = MutableLiveData<List<GradeJson>?>()
    var amountAttemptsToConnect = 1
    var chosenGradeId: Int? = null

    init{
        downloadGrades(chosenSchool.id)
    }

    private fun downloadGrades(schoolId: Int) {
        RequestManager.getGradesForSchool(schoolId) {
            if(it!=null) chosenGradeId
            liveDataListGrades.value = it
        }
    }
}
