package com.example.lyceumapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lyceumapp.MainActivity
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.ActivityNoGradesForSchoolBinding

class NoGradesForSchoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoGradesForSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textLikeButtonPickSchoolAgain.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}