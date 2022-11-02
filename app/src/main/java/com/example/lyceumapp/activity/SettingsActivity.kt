package com.example.lyceumapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lyceumapp.Const
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.textLikeButtonChangeSchoolAndGrade.setOnClickListener {
            val shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE)
            shPreferences.edit().remove(Const.SH_PREFERENCES_KEY_SCHOOL_ID)
                .remove(Const.SH_PREFERENCES_KEY_GRADE_ID)
                .remove(Const.SH_PREFERENCES_KEY_SCHOOL_NAME)
                .remove(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS).commit()
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.buttonCancel.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}