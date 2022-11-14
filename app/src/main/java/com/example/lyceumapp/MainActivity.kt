package com.example.lyceumapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.activity.ChooseGradeActivity
import com.example.lyceumapp.activity.MainMenuActivity
import com.example.lyceumapp.activity.NoResponseActivity
import com.example.lyceumapp.viewmodel.MainViewModel
import com.example.lyceumapp.choose_school_and_grade.ChooseSchoolFragment
import com.example.lyceumapp.databinding.ActivityChooseSchoolsOrGradesBinding
import com.example.lyceumapp.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        viewModel = ViewModelProvider(this, MainViewModelFactory(application, MODE_PRIVATE))[MainViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataDoWeHaveDataInShPreferences.observe(this) {
            if (it) {
                startActivity(Intent(applicationContext, MainMenuActivity::class.java))
                finish()
            }
        }

        viewModel.liveDataListSchools.observe(this) { schools ->
            if (schools == null) {
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetSchools)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            } else {
                val binding = ActivityChooseSchoolsOrGradesBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val fragmentSchools = ChooseSchoolFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(Const.BUNDLE_KEY_SCHOOLS_LIST, schools as ArrayList<out Parcelable>)
                fragmentSchools.arguments = bundle
                supportFragmentManager.beginTransaction().add(R.id.fragmentContainerChooseSchoolAndGrade, fragmentSchools).commit()
                binding.buttonNext.setOnClickListener {
                    val intent = Intent(this, ChooseGradeActivity::class.java)
                    intent.putExtra(Const.INTENT_KEY_CHOSEN_SCHOOL, fragmentSchools.adapter.chosenSchool)
                    startActivity(intent)
                }
            }
        }
    }


}

