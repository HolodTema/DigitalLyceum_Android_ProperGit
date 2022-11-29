package com.example.lyceumapp.tabs.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.Const
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentLessonsScheduleBinding
import com.example.lyceumapp.databinding.FragmentNoLessonsScheduleBinding
import com.example.lyceumapp.databinding.LessonInScheduleBinding
import com.example.lyceumapp.json.lessons.LessonJson

class LessonsScheduleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lessonsForDay = requireArguments().getParcelableArrayList<LessonJson>(Const.BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_SUBGROUP)!!

        return if(lessonsForDay.size>0){
            val bindingWeHaveLessons = FragmentLessonsScheduleBinding.inflate(layoutInflater)

            val adapter = LessonsAdapter(layoutInflater, lessonsForDay, resources.getString(R.string.lesson_time))
            bindingWeHaveLessons.recyclerLessons.adapter = adapter
            bindingWeHaveLessons.recyclerLessons.layoutManager = LinearLayoutManager(activity)
            bindingWeHaveLessons.recyclerLessons.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

            bindingWeHaveLessons.root
        } else FragmentNoLessonsScheduleBinding.inflate(layoutInflater).root
    }

    class LessonsAdapter(private val inflater: LayoutInflater, private val lessons: ArrayList<LessonJson>, private val strTime: String): RecyclerView.Adapter<LessonsAdapter.LessonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LessonHolder(LessonInScheduleBinding.inflate(inflater))

        override fun onBindViewHolder(holder: LessonHolder, position: Int) {
            val lesson = lessons[position]
            holder.binding.textLessonName.text = lesson.name
            holder.binding.textLessonNumber.text = position.toString()
            holder.binding.textLessonTime.text = String.format(strTime, lesson.startHour, lesson.startMinute, lesson.endHour, lesson.endMinute)
            holder.binding.textLessonTeacher.text = "Lawr, add teacherName, or teacher object field into lesson object"
            holder.binding.linearLessonIsNotRequired.visibility = View.GONE // TODO: Lawrence will add required field into lesson object soon...

        }

        override fun getItemCount() = lessons.size

        class LessonHolder(val binding: LessonInScheduleBinding) : RecyclerView.ViewHolder(binding.root)
    }
}