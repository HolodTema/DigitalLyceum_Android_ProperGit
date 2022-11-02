package com.example.lyceumapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.TabLayoutCanNotHaveMoreThan6TabsException
import com.example.lyceumapp.database.LessonDB
import com.example.lyceumapp.databinding.ActivityLessonsScheduleBinding
import com.example.lyceumapp.tabs.lessons.LessonsTabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LessonsScheduleActivity(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLessonsScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val lessonsForGrade = intent.extras!!.getParcelableArrayList<LessonDB>(Const.INTENT_KEY_LESSONS_FOR_GRADE)!!

        designTabLayout(binding)

        val adapter = LessonsTabsPagerAdapter(supportFragmentManager, lifecycle, lessonsForGrade)
        binding.viewPagerLessonsSchedule.adapter = adapter
        binding.viewPagerLessonsSchedule.isUserInputEnabled = true

        TabLayoutMediator(binding.tabLayoutLessonsSchedule, binding.viewPagerLessonsSchedule) { tab, position ->
            tab.text = when(position) {
                0 -> resources.getString(R.string.monday)
                1 -> resources.getString(R.string.tuesday)
                2 -> resources.getString(R.string.wednesday)
                3 -> resources.getString(R.string.thursday)
                4 -> resources.getString(R.string.friday)
                5 -> resources.getString(R.string.saturday)
                else -> throw TabLayoutCanNotHaveMoreThan6TabsException()
            }
        }.attach()

        binding.buttonCancel.setOnClickListener {
            startActivity(Intent(applicationContext, MainMenuActivity::class.java))
        }
    }

    private fun designTabLayout(binding: ActivityLessonsScheduleBinding) {
        binding.tabLayoutLessonsSchedule.setSelectedTabIndicatorColor(Color.WHITE)

        val typedValue = TypedValue()
        theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)
        binding.tabLayoutLessonsSchedule.setBackgroundColor(typedValue.data)

        binding.tabLayoutLessonsSchedule.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        binding.tabLayoutLessonsSchedule.tabMode = TabLayout.MODE_FIXED

        binding.tabLayoutLessonsSchedule.isInlineLabel = true
    }

}