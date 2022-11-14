package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.choose_school_and_grade.ChooseGradeFragment
import com.example.lyceumapp.databinding.ActivityChooseSchoolsOrGradesBinding
import com.example.lyceumapp.json.schools.SchoolJson
import com.example.lyceumapp.viewmodel.ChooseGradeViewModel
import com.example.lyceumapp.viewmodel.ChooseGradeViewModelFactory

class ChooseGradeActivity : AppCompatActivity() {
    private lateinit var viewModel: ChooseGradeViewModel
    private lateinit var chosenSchool: SchoolJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        chosenSchool = intent.extras?.getParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL)!!

        viewModel = ViewModelProvider(this, ChooseGradeViewModelFactory(application, chosenSchool))[ChooseGradeViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataListGrades.observe(this) { grades ->
            if(grades==null) {
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetGrades)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            }
            else {
                val binding = ActivityChooseSchoolsOrGradesBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val fragmentGrades = ChooseGradeFragment(viewModel)
                val bundle = Bundle()
                bundle.putParcelableArrayList(Const.BUNDLE_KEY_GRADES_LIST, grades as ArrayList<out Parcelable>)
                fragmentGrades.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerChooseSchoolAndGrade, fragmentGrades).commit()

                binding.buttonNext.setOnClickListener {
                    saveEverythingInShPreferences(chosenSchool.id, fragmentGrades.adapter.gradeId!!, chosenSchool.address, chosenSchool.name)
                    startActivity(Intent(this@ChooseGradeActivity, MainMenuActivity::class.java))
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Const.INTENT_KEY_CHOSEN_SCHOOL, chosenSchool)
        super.onSaveInstanceState(outState)
    }

    private fun saveEverythingInShPreferences(schoolId: Int, gradeId: Int, schoolAddress: String, schoolName: String) {
        val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
        //I don't want to use .apply() instead of .commit() 'cause I'm afraid that ShPreferences can be not saved by the moment We need them in next activity - MainMenuActivity
        shPreferences.edit()
            .putInt(Const.SH_PREFERENCES_KEY_SCHOOL_ID, schoolId)
            .putInt(Const.SH_PREFERENCES_KEY_GRADE_ID, gradeId) //our architecture guarantee that gradeId will haven't be null by this code.
            .putString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, schoolAddress)
            .putString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, schoolName)
            .commit()
    }
}