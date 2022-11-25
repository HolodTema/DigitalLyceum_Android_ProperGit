package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson

class ChooseSubgroupViewModel(
    application: Application,
    chosenGrade: GradeJson
): AndroidViewModel(application) {

    val liveDataListSubgroups = MutableLiveData<List<SubgroupJson>?>()
    //if even this attempt of downloading subgroups is the first (this case amountAttemptsToConnect
    //is always null), we still have default value = 1
    var amountAttemptsToConnect = 1
    var amountGrades = 1
    lateinit var chosenSchool: SchoolJson
    lateinit var chosenSubgroup: SubgroupJson

    init{
        downloadSubgroups(chosenGrade.id)
    }

    private fun downloadSubgroups(gradeId: Int) {
        RequestManager.getSubgroupsForGrade(gradeId) {
            if(it!=null && it.isNotEmpty()) chosenSubgroup = it[0]

            liveDataListSubgroups.value = it
        }
    }

}