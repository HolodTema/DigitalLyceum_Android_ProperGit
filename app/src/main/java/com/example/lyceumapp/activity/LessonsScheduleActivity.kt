package com.example.lyceumapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.TabLayoutCanNotHaveMoreThan6TabsException
import com.example.lyceumapp.databinding.ActivityLessonsScheduleBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.tabs.lessons.LessonsTabsPagerAdapter
import com.example.lyceumapp.viewmodel.LessonsScheduleViewModel
import com.example.lyceumapp.viewmodel.LessonsScheduleViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LessonsScheduleActivity(): AppCompatActivity() {
    private lateinit var viewModel: LessonsScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLessonsScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val lessonsForSubgroup = intent.extras?.getParcelableArrayList<LessonJson>(Const.INTENT_KEY_LESSONS_FOR_SUBGROUP)!!
        viewModel = ViewModelProvider(this, LessonsScheduleViewModelFactory(application, lessonsForSubgroup))[LessonsScheduleViewModel::class.java]

        designTabLayout(binding)

        //binding.textScheduleForGradeAndSubgroup.text = String.format(resources.getString(R.string.lesson_schedule_text_header), viewModel.

        val adapter = LessonsTabsPagerAdapter(supportFragmentManager, lifecycle, viewModel.lessonsForDefiniteWeek)
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

        if(viewModel.weekNames.size>0) {
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.weekNames)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerChooseWeek.adapter = spinnerAdapter
            binding.spinnerChooseWeek.setSelection(viewModel.chosenWeek)

            binding.spinnerChooseWeek.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.chosenWeek = position
                    viewModel.updateLessonsForDefiniteWeek()
                    adapter.lessons = viewModel.lessonsForDefiniteWeek
                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
        else {
            binding.spinnerChooseWeek.visibility = View.GONE
        }

        binding.buttonCancel.setOnClickListener {
            startActivity(Intent(applicationContext, MainMenuActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(Const.BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_SUBGROUP, viewModel.lessonsForSubgroup)
        super.onSaveInstanceState(outState)
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