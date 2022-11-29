package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityMainMenuBinding
import com.example.lyceumapp.viewmodel.MainMenuViewModel
import com.example.lyceumapp.viewmodel.MainMenuViewModelFactory

class MainMenuActivity : AppCompatActivity() {
    private lateinit var viewModel: MainMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this, MainMenuViewModelFactory(application))[MainMenuViewModel::class.java]

        val amountAttemptsToConnect = intent.extras?.getInt(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT)
        if(amountAttemptsToConnect!=null) viewModel.amountAttemptsToConnect = amountAttemptsToConnect

        viewModel.liveDataLessonsForSubgroup.observe(this, Observer { pairLessonsAndIsActual ->
            if(pairLessonsAndIsActual.first==null) {
                //something went wrong and we can't get lessons for the subgroup from the server. We start NoResponseActivity
                val intent = Intent(this, NoResponseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra(Const.INTENT_KEY_NO_RESPONSE_TYPE, Const.NoResponseType.GetLessons)
                intent.putExtra(Const.INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT, viewModel.amountAttemptsToConnect)
                startActivity(intent)
            }
            else {
                //there is we've finally got lessons. We can show main_menu layout!
                val binding = ActivityMainMenuBinding.inflate(layoutInflater)
                setContentView(binding.root)

                //we need to hide the red textView with attention about not actual lessons in case of isActual==true
                binding.textWarningLessonsMustNotBeActual.visibility = if (pairLessonsAndIsActual.second) View.GONE
                else View.VISIBLE

                binding.textSchoolAddress.text = viewModel.schoolAddress
                binding.textSchoolName.text = viewModel.schoolName

                // TODO: come up the engine that counts time to the next lesson

                binding.linearMenuElementScheduleLessons.setOnClickListener {
                    val intent = Intent(this@MainMenuActivity, LessonsScheduleActivity::class.java)
                    intent.putParcelableArrayListExtra(Const.INTENT_KEY_LESSONS_FOR_SUBGROUP, pairLessonsAndIsActual.first as java.util.ArrayList<out Parcelable>)
                    startActivity(intent)
                }

                binding.buttonSettings.setOnClickListener {
                    startActivity(Intent(this@MainMenuActivity, SettingsActivity::class.java))
                }
            }
        })
    }
}