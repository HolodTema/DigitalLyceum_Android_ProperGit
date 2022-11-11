package com.example.lyceumapp.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.lyceumapp.Const
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.json.schools.SchoolJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainActivity) {
    private var amountAttemptsToConnect = 0


    fun checkIfShPreferencesContainsData(context: Context, listener: (doWeHaveDataInShPreferences: Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                val shPreferences = context.getSharedPreferences(Const.SH_PREFERENCES_NAME, AppCompatActivity.MODE_PRIVATE)
                shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_GRADE_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_NAME)

            }
            val doWeHaveDataInShPreferences = deferred.await()
            if(doWeHaveDataInShPreferences) instance = null
            listener(deferred.await())
        }
    }

    fun tryToDownloadSchools() {
        amountAttemptsToConnect++
        RequestManager.getSchools{
            if (it == null) {
                val wasLimitAttemptsExceeded = amountAttemptsToConnect >= Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING
                if(wasLimitAttemptsExceeded) amountAttemptsToConnect = 0
                view.setNoResponseLayoutAfterDownloadingSchools(wasLimitAttemptsExceeded)
            } else {
                amountAttemptsToConnect = 0
                view.setChooseSchoolLayout(it)
            }
        }
    }

    fun tryToDownloadGrades(school: SchoolJson) {
        amountAttemptsToConnect++
        RequestManager.getGradesForSchool(school.id){
            if (it == null) {
                val wasLimitAttemptsExceeded = amountAttemptsToConnect >= Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING
                if(wasLimitAttemptsExceeded) amountAttemptsToConnect = 0
                view.setNoResponseLayoutAfterDownloadingGrades(wasLimitAttemptsExceeded, school)
            }
            else {
                amountAttemptsToConnect = 0
                view.setChooseGradeLayout(school, it)
            }
        }

    }




    companion object{
        private var instance: MainPresenter? = null
        fun getInstance(view: MainActivity): MainPresenter {
            return if(instance==null) {
                instance = MainPresenter(view)
                instance!!
            } else instance!!
        }
    }
}