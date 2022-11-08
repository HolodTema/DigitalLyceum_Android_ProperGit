package com.example.lyceumapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lyceumapp.RequestManager.OnResponseGotListener
import com.example.lyceumapp.activity.MainMenuActivity
import com.example.lyceumapp.choose_school_and_grade.ChooseGradeFragment
import com.example.lyceumapp.choose_school_and_grade.ChooseSchoolFragment
import com.example.lyceumapp.databinding.ActivityMainBinding
import com.example.lyceumapp.databinding.ActivityNoResponseBinding
import com.example.lyceumapp.json.grades.GradeJson
import com.example.lyceumapp.json.schools.SchoolJson
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var amountAttemptsToConnect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        supportActionBar?.hide()
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
                shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_GRADE_ID)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS)
                        && shPreferences.contains(Const.SH_PREFERENCES_KEY_SCHOOL_NAME)

            }
            if(deferred.await())  { //we don't need to load data from server // TODO: after Lavr make phone_number field in json on server
                startActivity(Intent(applicationContext, MainMenuActivity::class.java))
                finish()
            }
            else {
                tryToDownloadSchools()
            }
        }
    }

    private fun tryToDownloadSchools() {
        amountAttemptsToConnect++

        RequestManager.getSchools(object : OnResponseGotListener<List<SchoolJson>?> {
            override fun onResponseGot(obj: List<SchoolJson>?) {
                if (obj == null) {
                    val binding = ActivityNoResponseBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    if (amountAttemptsToConnect >= Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING) {
                        binding.textLikeButtonNoResponseTryAgain.visibility = View.GONE
                        binding.textYouSendSoManyRequests.visibility = View.VISIBLE
                        binding.textYouSendSoManyRequests.text = String.format(
                            resources.getString(R.string.amount_attempts_to_server_limit), Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT
                        )
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
                            amountAttemptsToConnect = 0
                        }
                    } else binding.textYouSendSoManyRequests.visibility = View.GONE

                    binding.textLikeButtonNoResponseTryAgain.setOnClickListener {
                        tryToDownloadSchools()
                    }
                } else {
                    val binding = ActivityMainBinding.inflate(layoutInflater)
                    setContentView(binding.root)

                    val fragmentSchools = ChooseSchoolFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(
                        Const.BUNDLE_KEY_SCHOOLS_LIST, obj as ArrayList<out Parcelable>
                    )
                    fragmentSchools.arguments = bundle
                    supportFragmentManager.beginTransaction().add(R.id.fragmentContainerChooseSchoolAndGrade, fragmentSchools).commit()

                    binding.buttonNext.setOnClickListener {
                        amountAttemptsToConnect = 0
                        tryToDownloadGrades(fragmentSchools.adapter?.chosenSchool!!) //our architecture requires that adapter can't be null by this moment of code
                    }
                }
            }
        })
    }

    private fun tryToDownloadGrades(school: SchoolJson) {
        setContentView(R.layout.activity_download)
        amountAttemptsToConnect++

        RequestManager.getGradesForSchool(school.id, object : OnResponseGotListener<List<GradeJson>?> {
            override fun onResponseGot(obj: List<GradeJson>?) {
                if (obj == null) {
                    val binding = ActivityNoResponseBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    if (amountAttemptsToConnect >= Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING) {
                        binding.textLikeButtonNoResponseTryAgain.visibility = View.GONE
                        binding.textYouSendSoManyRequests.visibility = View.VISIBLE
                        binding.textYouSendSoManyRequests.text = String.format(
                            resources.getString(R.string.amount_attempts_to_server_limit), Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT
                        )
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
                            amountAttemptsToConnect = 0
                        }
                    } else binding.textYouSendSoManyRequests.visibility = View.GONE

                    binding.textLikeButtonNoResponseTryAgain.setOnClickListener {
                        tryToDownloadGrades(school)
                    }
                } else {
                    val binding = ActivityMainBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    val fragmentGrades = ChooseGradeFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(
                        Const.BUNDLE_KEY_GRADES_LIST, obj as ArrayList<out Parcelable>
                    )
                    fragmentGrades.arguments = bundle
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerChooseSchoolAndGrade, fragmentGrades).commit()
                    binding.buttonNext.setOnClickListener {
                        val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
                        shPreferences.edit().putInt(Const.SH_PREFERENCES_KEY_SCHOOL_ID, school.id).putInt(
                                Const.SH_PREFERENCES_KEY_GRADE_ID, fragmentGrades.adapter?.gradeId!!
                            ).putString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, school.address).putString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, school.name)
                            //I don't want to use .apply() instead of .commit() 'cause I'm afraid that ShPreferences can be not saved by the moment We need them in next activity - MainMenuActivity (we launch this activity below)
                            .commit() //our architecture guarantee that gradeId will haven't be null by this code.
                        startActivity(Intent(this@MainActivity, MainMenuActivity::class.java))
                    }
                }
            }
        })

    }

    companion object{
        private var isCoroutineAlreadyLaunched = false
    }
}

