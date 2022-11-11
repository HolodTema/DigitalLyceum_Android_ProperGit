package com.example.lyceumapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lyceumapp.activity.MainMenuActivity
import com.example.lyceumapp.activity.MainPresenter
import com.example.lyceumapp.choose_school_and_grade.ChooseGradeFragment
import com.example.lyceumapp.choose_school_and_grade.ChooseSchoolFragment
import com.example.lyceumapp.databinding.ActivityMainBinding
import com.example.lyceumapp.databinding.ActivityNoResponseBinding
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var mainPresenter: MainPresenter? = null
    private var isLaunchTheFirst = true
    private var state: States = States.Downloading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isLaunchTheFirst) {
            isLaunchTheFirst = false
            state = States.Downloading
            setContentView(R.layout.activity_download)

            supportActionBar?.hide()

            mainPresenter = MainPresenter.getInstance(this)
            mainPresenter?.checkIfShPreferencesContainsData(applicationContext) { doWeHaveDataInShPreferences ->
                if(doWeHaveDataInShPreferences) {
                    startActivity(Intent(applicationContext, MainMenuActivity::class.java))
                    finish()
                }
                else {
                    mainPresenter?.tryToDownloadSchools()
                }
            }
        }
        else {

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(Const.BUNDLE_KEY_ACTIVITY_MAIN_STATE)
        super.onSaveInstanceState(outState)
    }

    fun setNoResponseLayoutAfterDownloadingSchools(wasLimitExceeded: Boolean) {
        val binding = ActivityNoResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(wasLimitExceeded) {
            state = States.NoResponseLimitAttemptsExceeded
            binding.textLikeButtonNoResponseTryAgain.visibility = View.GONE
            binding.textYouSendSoManyRequests.visibility = View.VISIBLE
            binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT)
            CoroutineScope(Dispatchers.Main).launch {
                for (i in Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT - 1 downTo 0) {
                    val deferred = CoroutineScope(Dispatchers.Default).async {
                        delay(1000)
                    }
                    deferred.await()
                    binding.textYouSendSoManyRequests.text = String.format(
                        resources.getString(R.string.amount_attempts_to_server_limit), i
                    )
                }
                binding.textYouSendSoManyRequests.visibility = View.GONE
                binding.textLikeButtonNoResponseTryAgain.visibility = View.VISIBLE
            }
        }
        else {
            state = States.NoResponse
            binding.textYouSendSoManyRequests.visibility = View.GONE
        }

        binding.textLikeButtonNoResponseTryAgain.setOnClickListener {
            state = States.Downloading
            setContentView(R.layout.activity_download)
            mainPresenter?.tryToDownloadSchools()
        }
    }

    fun setNoResponseLayoutAfterDownloadingGrades(wasLimitExceeded: Boolean, school: SchoolJson) {
        val binding = ActivityNoResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(wasLimitExceeded) {
            state = States.NoResponseLimitAttemptsExceeded
            binding.textLikeButtonNoResponseTryAgain.visibility = View.GONE
            binding.textYouSendSoManyRequests.visibility = View.VISIBLE
            binding.textYouSendSoManyRequests.text = String.format(resources.getString(R.string.amount_attempts_to_server_limit), Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT)
            CoroutineScope(Dispatchers.Main).launch {
                for (i in Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT - 1 downTo 0) {
                    val deferred = CoroutineScope(Dispatchers.Default).async {
                        delay(1000)
                    }
                    deferred.await()
                    binding.textYouSendSoManyRequests.text = String.format(
                        resources.getString(R.string.amount_attempts_to_server_limit), i
                    )
                }
                binding.textYouSendSoManyRequests.visibility = View.GONE
                binding.textLikeButtonNoResponseTryAgain.visibility = View.VISIBLE
            }
        }
        else {
            state = States.NoResponse
            binding.textYouSendSoManyRequests.visibility = View.GONE
        }

        binding.textLikeButtonNoResponseTryAgain.setOnClickListener {
            state = States.Downloading
            setContentView(R.layout.activity_download)
            mainPresenter?.tryToDownloadGrades(school)
        }
    }

    fun setChooseSchoolLayout(schools: List<SchoolJson>) {
        state = States.ChooseSchool
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentSchools = ChooseSchoolFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(Const.BUNDLE_KEY_SCHOOLS_LIST, schools as ArrayList<out Parcelable>)
        fragmentSchools.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerChooseSchoolAndGrade, fragmentSchools).commit()

        binding.buttonNext.setOnClickListener {
            state = States.Downloading
            setContentView(R.layout.activity_download)
            mainPresenter?.tryToDownloadGrades(fragmentSchools.adapter?.chosenSchool!!) //our architecture requires that adapter can't be null by this moment of code
        }
    }

    fun setChooseGradeLayout(school: SchoolJson, grades: List<GradeJson>) {
        state = States.ChooseGrade
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentGrades = ChooseGradeFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(
            Const.BUNDLE_KEY_GRADES_LIST, grades as ArrayList<out Parcelable>
        )
        fragmentGrades.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerChooseSchoolAndGrade, fragmentGrades).commit()
        binding.buttonNext.setOnClickListener {
            //I don't want to use .apply() instead of .commit() 'cause I'm afraid that ShPreferences can be not saved by the moment We need them in next activity - MainMenuActivity (we launch this activity below)
            val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
            shPreferences.edit()
                .putInt(Const.SH_PREFERENCES_KEY_SCHOOL_ID, school.id)
                .putInt(Const.SH_PREFERENCES_KEY_GRADE_ID, fragmentGrades.adapter?.gradeId!!) //our architecture guarantee that gradeId will haven't be null by this code.
                .putString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, school.address)
                .putString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, school.name)
                .commit()
            startActivity(Intent(this@MainActivity, MainMenuActivity::class.java))
        }
    }

    enum class States{
        Downloading,
        NoResponse,
        NoResponseLimitAttemptsExceeded,
        ChooseSchool,
        ChooseGrade
    }
}

