package com.example.lyceumapp.tabs.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.BUNDLE_KEY_WEEKDAY
import com.example.lyceumapp.NoLessonsWhenFragmentCreatedException
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentLessonsScheduleBinding
import com.example.lyceumapp.databinding.FragmentNoLessonsScheduleBinding
import com.example.lyceumapp.databinding.LessonInScheduleInactiveBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.viewmodel.MainMenuViewModel

@Suppress("ConvertSecondaryConstructorToPrimary")
class LessonsScheduleFragment() : Fragment() {
    private val viewModel: MainMenuViewModel by activityViewModels()
    private var weekday: Int? = null
    constructor(weekday: Int): this(){
        this.weekday = weekday
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentLessonsScheduleBinding.inflate(layoutInflater)

        if(savedInstanceState!=null) {
            weekday = savedInstanceState.getInt(BUNDLE_KEY_WEEKDAY)
        }

        val dayLessons = viewModel.getDayLessons(weekday!!)

        if(isAdded && dayLessons.isNotEmpty()) {
            binding.textNoLessons.visibility = View.GONE

            val adapter = LessonsAdapter(layoutInflater, dayLessons, resources.getString(R.string.lesson_time))
            binding.recyclerLessons.adapter = adapter
        }
        else {
            binding.recyclerLessons.visibility = View.GONE
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUNDLE_KEY_WEEKDAY, weekday!!)
    }

    class LessonsAdapter(private val inflater: LayoutInflater, private val lessons: List<LessonJson>, private val strTime: String): RecyclerView.Adapter<LessonsAdapter.LessonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LessonHolder(LessonInScheduleInactiveBinding.inflate(inflater, parent, false))

        override fun onBindViewHolder(holder: LessonHolder, position: Int) {
            val lesson = lessons[position]
            holder.binding.textLessonName.text = lesson.name
            holder.binding.textLessonTime.text = String.format(strTime, lesson.startTime.hour, lesson.startTime.minute, lesson.endTime.hour, lesson.endTime.minute)
            holder.binding.textLessonTeacher.text = lesson.teacher.name
            holder.binding.textLessonChamber.text = lesson.room
        }

        override fun getItemCount() = lessons.size

        class LessonHolder(val binding: LessonInScheduleInactiveBinding) : RecyclerView.ViewHolder(binding.root)
    }
}