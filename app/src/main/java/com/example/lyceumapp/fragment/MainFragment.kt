package com.example.lyceumapp.fragment

import java.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lyceumapp.R
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.databinding.FragmentMainBinding
import com.example.lyceumapp.databinding.LessonInScheduleInactiveBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.viewmodel.MainMenuViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainMenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //our viewModel instance by our MainMenuActivity
        //actually binding with 3 params including container and attachToParent is better, than simple 1-parameter method
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //the main fragment's idea is immutability of fragment like a finished ui component
        //we can't reset fragment's layout to another after onCreateView().
        //But we can have a case when we need to set layout according to data from viewModel, but there is observer, not straight
        //call in onCreateView - we can't call 'return binding.root' from observer.
        //the best solution is creating elements for every case. So, I created textNoLessons and linearScheduleMain for 2 different cases
        viewModel.liveDataNearestDayLessons.observe(requireActivity()) {nearestSchedule ->
            if(nearestSchedule==null || nearestSchedule.lessons.isEmpty()) {
                binding.textNoLessons.visibility = View.VISIBLE
                binding.linearScheduleMain.visibility = View.GONE
                binding.textNearestDayWithLessons.visibility = View.GONE
            }
            else {
                val weekdayCurrentInServerFormat = RequestManager.dayCalendarFormatTo0to6(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
                if(weekdayCurrentInServerFormat==nearestSchedule.lessons[0].weekday) {
                    binding.textNoLessons.visibility = View.GONE
                    binding.textNearestDayWithLessons.visibility = View.GONE
                }
                else {
                    binding.textNearestDayWithLessons.append(" ${serverWeekDayNumberToStr(nearestSchedule.lessons[0].weekday)}")
                }

                binding.textDayOfWeek.text = serverWeekDayNumberToStr(weekdayCurrentInServerFormat)

                var bindingLessonInactiveElement: LessonInScheduleInactiveBinding
                val strTime = resources.getString(R.string.lesson_time)
                for(lesson in nearestSchedule.lessons) {
                    bindingLessonInactiveElement = LessonInScheduleInactiveBinding.inflate(layoutInflater)
                    bindingLessonInactiveElement.textLessonName.text = lesson.name
                    bindingLessonInactiveElement.textLessonTime.text = String.format(strTime, lesson.startTime.hour, lesson.startTime.minute, lesson.endTime.hour, lesson.endTime.minute)
                    bindingLessonInactiveElement.textLessonTeacher.text = lesson.teacher.name
                    bindingLessonInactiveElement.textLessonChamber.text = lesson.room
                    binding.linearScheduleMain.addView(bindingLessonInactiveElement.root)
                }
            }
        }

        viewModel.liveDataWeekLessons.observe(requireActivity()) {
            configureDayType(it)
        }

        //there's button 'schedule' above linearScheduleMain
        binding.buttonSchedule.setOnClickListener {
            //updateChosenNavView is very cool method I've created for simple jumping between fragment in navView
            viewModel.updateChosenNavViewItemId(R.id.menuItemSchedule)
        }

        binding.buttonEvents.setOnClickListener {
            viewModel.updateChosenNavViewItemId(R.id.menuItemEvents)
        }

        return binding.root
    }

    private fun configureDayType(weekLessons: List<LessonJson>) {
        val todayInServerFormat = RequestManager.dayCalendarFormatTo0to6(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        viewModel.checkIfLessonsToday(weekLessons, todayInServerFormat) {areLessonsToday ->
            binding.progressDayTypeAndEvents.visibility = View.GONE
            binding.linearDayTypeAndEvents.visibility = View.VISIBLE

            binding.textDayType.text = if(areLessonsToday) getString(R.string.ordinary_school_day)
            else getString(R.string.weekend)

            val imageResDayType = if(areLessonsToday) R.drawable.day_type_ordinary
            else R.drawable.day_type_weekend
            binding.imageDayType.setImageResource(imageResDayType)

        }
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