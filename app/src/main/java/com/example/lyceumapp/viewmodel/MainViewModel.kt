package com.example.lyceumapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.SH_PREFERENCES_KEY_SUBGROUP_ID
import com.example.lyceumapp.SH_PREFERENCES_NAME
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val liveDataListSchools = MutableLiveData<List<SchoolJson>?>()

    //we use this liveData to start MainMenuActivity if we have actual info about school/grade/subgroup
    val liveDataStartMainMenuActivity = MutableLiveData<Pair<SchoolJson, Pair<GradeJson, SubgroupJson>>>()

    var amountAttemptsToConnect = 1
    lateinit var chosenSchool: SchoolJson

    //first we need to check if subgroupId exists in shPreferences
    //if yes - user has already chosen school and grade before, but we also need to check it:
        //we download subgroupJson, gradeJson, schoolJson from server step by step.
            //if something from them above is null, we need to download all schools from server
            //else, if we get everything, we start MainMenuActivity
    //if no - user hasn't chosen school and grade yet. - we need to download schools from server
    init{
        getShPreferencesData { subgroupId ->
            if(subgroupId==null) {
                //here we need to download schools for user
                downloadSchools()
            }
            else {
                //here we have subgroupId in shPreferences, but also we need to check
                //is this id is actual
                downloadChosenEarlierSubgroup(subgroupId) { subgroup ->
                    if(subgroup==null) {
                        //here subgroupId is not actual, we need to download schools for user
                        downloadSchools()
                    }
                    else {
                        //everything is ok with subgroup, but chosen earlier grade and school - they need checking
                        downloadChosenEarlierGrade(subgroup.gradeId) { grade ->
                            if(grade==null) {
                                //here gradeId is not actual, we need to download schools for user
                                downloadSchools()
                            }
                            else {
                                //everything is ok for subgroup and grade, but we don't know if school is actual
                                downloadChosenEarlierSchool(grade.schoolId) { school ->
                                    if(school==null) {
                                        //here schoolId is not actual, we need to download schools for user
                                        downloadSchools()
                                    }
                                    else {
                                        //everything is alright, we need to start MainMenuActivity inside MainActivity
                                        //but we can't do it from here, we need to call activity using livedata
                                        liveDataStartMainMenuActivity.value = school to (grade to subgroup)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //actually only subgroupId needs keeping in shPreferences
    private fun getShPreferencesData(listener: (Int?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                val shPreferences = getApplication<Application>().getSharedPreferences(SH_PREFERENCES_NAME, Context.MODE_PRIVATE)
                if(shPreferences.contains(SH_PREFERENCES_KEY_SUBGROUP_ID)) {
                    shPreferences.getInt(SH_PREFERENCES_KEY_SUBGROUP_ID, -1)
                }
                else null
            }
            listener(deferred.await())
        }
    }


    //RequestManager is like Repository or Model in MVVM pattern
    //default value of chosenSchool is the first object from the list of schools
    private fun downloadSchools() {
        RequestManager.getSchools{
            if(!it.isNullOrEmpty()) {
                chosenSchool = it[0]
            }
            liveDataListSchools.value = it
        }
    }

    private fun downloadChosenEarlierSubgroup(subgroupId: Int, listener: (SubgroupJson?) -> Unit) {
        RequestManager.getDefineSubgroup(subgroupId) {
            listener(it)
        }
    }

    private fun downloadChosenEarlierGrade(gradeId: Int, listener: (GradeJson?) -> Unit) {
        RequestManager.getDefineGrade(gradeId) {
            listener(it)
        }
    }

    private fun downloadChosenEarlierSchool(schoolId: Int, listener: (SchoolJson?) -> Unit) {
        RequestManager.getDefineSchool(schoolId) {
            listener(it)
        }
    }

}