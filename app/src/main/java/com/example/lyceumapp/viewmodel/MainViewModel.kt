package com.example.lyceumapp.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.Const
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.activity.MainMenuActivity
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.json.subgroups.SubgroupInfoJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val modeToOpenShPreferences: Int): AndroidViewModel(application) {
    val liveDataListSchools = MutableLiveData<List<SchoolJson>?>()

    var amountAttemptsToConnect = 1
    lateinit var chosenSchool: SchoolJson

    //first we need to check if subgroupId exists in shPreferences
    //if yes - user has already chosen school and grade before, but we also need to check it:
        //we download subgroupInfoJson from server with info about chosen subgroup, school and grade
            //if subgroupInfo is null, then our choice isn't actual, we need to download schools from server
            //else, if we get subgroupInfo, we start MainMenuActivity
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
                downloadSubgroupInfo(subgroupId) { subgroupInfo ->
                    if(subgroupInfo==null) {
                        //here subgroupId is not actual, we need to download schools for user
                        downloadSchools()
                    }
                    else {
                        //everything is alright, we need to start MainMenuActivity inside MainActivity
                        val intent = Intent(getApplication<Application>().applicationContext, MainMenuActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        intent.putExtra(Const.INTENT_KEY_SUBGROUP_INFO, subgroupInfo)
                        getApplication<Application>().startActivity(intent)
                    }
                }
            }
        }
    }

    private fun getShPreferencesData(listener: (Int?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                val shPreferences = getApplication<Application>().getSharedPreferences(Const.SH_PREFERENCES_NAME, modeToOpenShPreferences)
                if(shPreferences.contains(Const.SH_PREFERENCES_KEY_SUBGROUP_ID)) {
                    shPreferences.getInt(Const.SH_PREFERENCES_KEY_SUBGROUP_ID, -1)
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
            if(it!=null && it.isNotEmpty()) {
                chosenSchool = it[0]
            }
            liveDataListSchools.value = it
        }
    }

    private fun downloadSubgroupInfo(subgroupId: Int, listener: (SubgroupInfoJson?) -> Unit) {
        RequestManager.getSubgroupInfo(subgroupId) {
            listener(it)
        }
    }

}