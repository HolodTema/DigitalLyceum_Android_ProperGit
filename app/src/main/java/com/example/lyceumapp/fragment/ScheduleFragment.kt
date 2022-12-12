package com.example.lyceumapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lyceumapp.*
import com.example.lyceumapp.databinding.FragmentScheduleBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.tabs.lessons.LessonsScheduleFragment
import com.example.lyceumapp.viewmodel.MainMenuViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)

        val viewModel: MainMenuViewModel by activityViewModels()

        val weekNames = viewModel.getWeekNamesForSubgroup()
        if(weekNames.isEmpty()) binding.linearWeekType.visibility = View.GONE
        else {
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, weekNames)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerWeekType.adapter = spinnerAdapter
        }



        val lessonsForDefiniteWeek = viewModel.getLessonsForDefiniteWeek(0)
        val adapter = LessonsTabsPagerAdapter(viewModel, childFragmentManager, lifecycle, lessonsForDefiniteWeek)
        binding.viewPagerSchedule.adapter = adapter
        binding.viewPagerSchedule.isUserInputEnabled = true
        TabLayoutMediator(binding.tabLayoutSchedule, binding.viewPagerSchedule) { tab, position ->
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

        binding.spinnerWeekType.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.lessons = viewModel.getLessonsForDefiniteWeek(position)
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }

        binding.buttonChangeGrade.setOnClickListener {
            val shPreferences = requireActivity().getSharedPreferences(Const.SH_PREFERENCES_NAME, Context.MODE_PRIVATE)
            shPreferences.edit().remove(Const.SH_PREFERENCES_KEY_SCHOOL_ID)
                .remove(Const.SH_PREFERENCES_KEY_GRADE_ID)
                .remove(Const.SH_PREFERENCES_KEY_SUBGROUP_ID)
                .remove(Const.SH_PREFERENCES_KEY_SCHOOL_NAME)
                .remove(Const.SH_PREFERENCES_KEY_SCHOOL_ADDRESS).commit()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        return binding.root
    }

    class LessonsTabsPagerAdapter(private var viewModel: MainMenuViewModel,
                                  fragmentManager: FragmentManager,
                                  lifecycle: Lifecycle,
                                  var lessons: List<LessonJson>): FragmentStateAdapter(fragmentManager, lifecycle){

        override fun getItemCount() = 6

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0, 1, 2, 3, 4, 5 -> {
                    viewModel.updateLessonsForDefiniteDay(lessons, RequestManager.day0to6toCalendarFormat(position))
                    LessonsScheduleFragment()
                }
                else -> throw LessonOutOfBoundsException()
            }
        }

    }

}