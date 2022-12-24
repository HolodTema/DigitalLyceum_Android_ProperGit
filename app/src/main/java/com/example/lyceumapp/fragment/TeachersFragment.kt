package com.example.lyceumapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentTeachersBinding
import com.example.lyceumapp.databinding.RecyclerElementTeachersBinding
import com.example.lyceumapp.json.teachers.TeacherJson
import com.example.lyceumapp.viewmodel.MainMenuViewModel

class TeachersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: MainMenuViewModel by activityViewModels()
        val binding = FragmentTeachersBinding.inflate(layoutInflater)

        viewModel.liveDataTeachers.observe(requireActivity()) { teachers ->
            if(teachers!=null) {
                val adapter = TeacherAdapter(teachers, layoutInflater)
                binding.recyclerTeachers.layoutManager = LinearLayoutManager(activity)
                binding.recyclerTeachers.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                binding.recyclerTeachers.adapter = adapter
            }
        }

        return binding.root
    }

    class TeacherAdapter(private val teachers: List<TeacherJson>, private val inflater: LayoutInflater): RecyclerView.Adapter<TeacherAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(RecyclerElementTeachersBinding.inflate(inflater))

        override fun onBindViewHolder(holder: Holder, position: Int) {
//            holder.binding.textName.text = teachers[position]
        }

        override fun getItemCount() = teachers.size

        class Holder(val binding: RecyclerElementTeachersBinding): RecyclerView.ViewHolder(binding.root)
    }
}