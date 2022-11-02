package com.example.lyceumapp.tabs.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.view.marginBottom
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.database.LessonDB
import com.example.lyceumapp.databinding.FragmentLessonsScheduleBinding
import com.example.lyceumapp.databinding.FragmentNoLessonsScheduleBinding
import com.example.lyceumapp.databinding.LessonInScheduleBinding

class LessonsScheduleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lessonsForDay = requireArguments().getParcelableArrayList<LessonDB>(Const.BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_GRADE)!!

        return if(lessonsForDay.size>0){
            val bindingWeHaveLessons = FragmentLessonsScheduleBinding.inflate(layoutInflater)
            val bindingLessonElement = LessonInScheduleBinding.inflate(layoutInflater)
            for((i, e) in lessonsForDay.withIndex()) {
                bindingLessonElement.textLessonName.text = e.name
                bindingLessonElement.textLessonNumber.text = (i+1).toString()
                bindingLessonElement.textLessonTime.text = String.format(resources.getString(R.string.lesson_time), e.startHour, e.startMinute, e.endHour, e.endMinute)
                bindingLessonElement.textLessonTeacher.text = e.teacher
                bindingLessonElement.linearLessonIsNotRequired.visibility = if(e.required) View.GONE else View.VISIBLE
                bindingWeHaveLessons.linearLessonsSchedule.addView(bindingLessonElement.root)

            }
            bindingWeHaveLessons.root
        } else FragmentNoLessonsScheduleBinding.inflate(layoutInflater).root
    }


}