package com.example.lyceumapp.tabs.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.NoLessonsWhenFragmentCreatedException
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentLessonsScheduleBinding
import com.example.lyceumapp.databinding.FragmentNoLessonsScheduleBinding
import com.example.lyceumapp.databinding.LessonInScheduleInactiveBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.viewmodel.MainMenuViewModel

class LessonsScheduleFragment : Fragment() {
    private val viewModel: MainMenuViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val lessons = viewModel.liveDataLessonsForDefiniteDay.value

        if(lessons!=null) {
            return if(lessons.isEmpty()) FragmentNoLessonsScheduleBinding.inflate(layoutInflater).root
            else {
                val binding = FragmentLessonsScheduleBinding.inflate(layoutInflater)
                val adapter = LessonsAdapter(layoutInflater, lessons, resources.getString(R.string.lesson_time))
                binding.recyclerLessons.adapter = adapter
                binding.recyclerLessons.layoutManager = LinearLayoutManager(activity)
                binding.recyclerLessons.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                binding.root
            }
        }
        else throw NoLessonsWhenFragmentCreatedException()
    }

    class LessonsAdapter(private val inflater: LayoutInflater, private val lessons: List<LessonJson>, private val strTime: String): RecyclerView.Adapter<LessonsAdapter.LessonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LessonHolder(LessonInScheduleInactiveBinding.inflate(inflater))

        override fun onBindViewHolder(holder: LessonHolder, position: Int) {
            val lesson = lessons[position]
            holder.binding.textLessonName.text = lesson.name
            holder.binding.textLessonTime.text = String.format(strTime, lesson.startTime.hour, lesson.startTime.minute, lesson.endTime.hour, lesson.endTime.minute)
        }

        override fun getItemCount() = lessons.size

        class LessonHolder(val binding: LessonInScheduleInactiveBinding) : RecyclerView.ViewHolder(binding.root)
    }
}