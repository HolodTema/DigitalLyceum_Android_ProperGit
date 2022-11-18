package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.Const
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.schools.SchoolJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val modeToOpenShPreferences: Int): AndroidViewModel(application) {
    val liveDataDoWeHaveDataInShPreferences = MutableLiveData<Boolean>()
    val liveDataListSchools = MutableLiveData<List<SchoolJson>?>()

    var amountAttemptsToConnect = 1
    lateinit var chosenSchool: SchoolJson

    init{
        checkIfShPreferencesContainsData()
    }

    private fun checkIfShPreferencesContainsData() {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                val shPreferences = getApplication<Application>().getSharedPreferences(Const.SH_PREFERENCES_NAME, modeToOpenShPreferences)
                val result = shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_GRADE_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_NAME)
                result
            }
            val result = deferred.await()
            liveDataDoWeHaveDataInShPreferences.value = result
            if(!result) {
                downloadSchools()
            }

        }
    }

    private fun downloadSchools() {
        RequestManager.getSchools{
            if(it!=null) chosenSchool = it[0]
            liveDataListSchools.value = it
        }
    }

}