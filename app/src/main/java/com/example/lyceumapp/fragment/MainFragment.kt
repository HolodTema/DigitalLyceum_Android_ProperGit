package com.example.lyceumapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentMainBinding
import com.example.lyceumapp.databinding.LessonInScheduleBinding
import com.example.lyceumapp.viewmodel.MainMenuViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //our viewModel instance by our MainMenuActivity
        //actually binding with 3 params including container and attachToParent is better, than simple 1-parameter method
        val viewModel: MainMenuViewModel by activityViewModels()
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        //the main fragment's idea is immutability of fragment like a finished ui component
        //we can't reset fragment's layout to another after onCreateView().
        //But we can have a case when we need to set layout according to data from viewModel, but there is observer, not straight
        //call in onCreateView - we can't call 'return binding.root' from observer.
        //the best solution is creating elements for every case. So, I created textNoLessons and linearScheduleMain for 2 different cases
        viewModel.liveDataTodaySchedule.observe(requireActivity()){ todaySchedule ->
            if(todaySchedule==null) {
                //there is no schedule for today from server.
                //maybe today is Sunday, but maybe there's an error on server...
                binding.textNoLessons.visibility = View.VISIBLE
                binding.linearScheduleMain.visibility = View.GONE
            }
            else {
                //we don't use recyclerView here, because we need to create scrolling ui in MainFragment for user.
                binding.textNoLessons.visibility = View.GONE
                binding.linearScheduleMain.visibility = View.VISIBLE

                binding.textDayOfWeek.text = serverWeekDayNumberToStr(todaySchedule.weekday)

                var bindingLessonElement: LessonInScheduleBinding
                val strTime = resources.getString(R.string.lesson_time)
                for(lesson in todaySchedule.lessons) {
                    bindingLessonElement = LessonInScheduleBinding.inflate(layoutInflater)
                    bindingLessonElement.textLessonName.text = lesson.name
                    bindingLessonElement.textLessonTime.text = String.format(strTime, lesson.startHour, lesson.startMinute, lesson.endHour, lesson.endMinute)
                    binding.linearScheduleMain.addView(bindingLessonElement.root)
                }
            }
        }

        viewModel.liveDataNextLessonAndTimeToIt.observe(requireActivity()){
            if(it==null) binding.textTimeToNextLesson.visibility = View.GONE // TODO: think what better: hiding textView or set text kinda 'no the soonest lesson'
            else {
                binding.textTimeToNextLesson.visibility = View.VISIBLE
                binding.textTimeToNextLesson.text = String.format(resources.getString(R.string.next_lesson_is_in_time), it.second.days, it.second.hours, it.second.minutes)
            }
        }


        //there's button 'schedule' above linearScheduleMain
        binding.buttonSchedule.setOnClickListener {
            //updateChosenNavView is very cool method I've created for simple jumping between fragment in navView
            viewModel.updateChosenNavViewItemId(R.id.menuItemSchedule)
        }

        return binding.root
    }

    private fun serverWeekDayNumberToStr(weekday: Int): String {
        //actually we have 3 systems of day's numbering:
        //1. 0..6 number segment like [monday; sunday]
        //2. response from server in weekday field. Now it's like 1st system,
        //but nobody knows what changes can happen, so it's different system
        //3. java.util.Calendar constants, for example Calendar.MONDAY, Calendar.THURSDAY etc.
        val daysStr = resources.getStringArray(R.array.daysOfWeek)
        return daysStr[weekday]
    }
}