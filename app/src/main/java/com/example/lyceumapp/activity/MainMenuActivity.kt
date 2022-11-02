package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lyceumapp.Const
import com.example.lyceumapp.GradeIdDidNotSavedInShPreferencesException
import com.example.lyceumapp.R
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.database.LessonDB
import com.example.lyceumapp.databinding.ActivityMainMenuBinding
import com.example.lyceumapp.databinding.ActivityNoResponseBinding

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        supportActionBar?.hide()
        val gradeId = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE).getInt(Const.SH_PREFERENCES_KEY_GRADE_ID, -1)
        if (gradeId == -1) throw GradeIdDidNotSavedInShPreferencesException()

        getScheduleForGrade(gradeId)
    }

    private fun getScheduleForGrade(gradeId: Int) {
        setContentView(R.layout.activity_download)
        RequestManager.getScheduleForGrade(applicationContext, gradeId, object : RequestManager.OnResponseGotListener<ArrayList<LessonDB>?> {
            override fun onResponseGot(obj: ArrayList<LessonDB>?) {
                if (obj == null) {
                    val binding = ActivityNoResponseBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    binding.textLikeButtonNoResponseTryAgain.setOnClickListener {
                        getScheduleForGrade(gradeId)
                    }
                } else doWhenWeGotScheduleForGrade(obj)
            }
        })
    }

    private fun doWhenWeGotScheduleForGrade(obj: ArrayList<LessonDB>) {
        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textWarningLessonsMustNotBeActual.visibility = if (RequestManager.isRequestScheduleForGradeAlreadyGot) View.GONE
        else View.VISIBLE

        val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
        binding.textSchoolAddress.text = shPreferences.getString(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS, resources.getString(R.string.no_school_address))
        binding.textSchoolName.text = shPreferences.getString(Const.SH_PREFERENCES_KEY_SCHOOL_NAME, resources.getString(R.string.no_school_name))

        val objForNextLessonEngine = RequestManager.getNextLessonAndTimeToIt(obj)
        binding.textNextLessonName.text = objForNextLessonEngine.first.name
        binding.textNextLessonTimes.text = String.format(resources.getString(R.string.lesson_time), objForNextLessonEngine.first.startHour, objForNextLessonEngine.first.startMinute, objForNextLessonEngine.first.endHour, objForNextLessonEngine.first.endMinute)
        binding.textNextLessonIsIn.text = String.format(resources.getString(R.string.next_lesson_is_in_time), objForNextLessonEngine.second.first, objForNextLessonEngine.second.second)

        binding.linearMenuElementScheduleLessons.setOnClickListener {
            val intent = Intent(this@MainMenuActivity, LessonsScheduleActivity::class.java)
            intent.putParcelableArrayListExtra(Const.INTENT_KEY_LESSONS_FOR_GRADE, obj)
            startActivity(intent)
        }

        binding.buttonSettings.setOnClickListener {
            startActivity(Intent(this@MainMenuActivity, SettingsActivity::class.java))
        }
    }
}