package com.example.lyceumapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lyceumapp.*
import com.example.lyceumapp.databinding.FragmentScheduleBinding
import com.example.lyceumapp.json.lessons.LessonJson
import com.example.lyceumapp.json.lessons.ScheduleJson
import com.example.lyceumapp.tabs.lessons.LessonsScheduleFragment
import com.example.lyceumapp.viewmodel.MainMenuViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)

        val viewModel: MainMenuViewModel by activityViewModels()

        binding.tabLayoutSchedule.visibility = View.GONE
        binding.viewPagerSchedule.visibility = View.GONE

        viewModel.liveDataWeekLessons.observe(requireActivity()) {weekLessons ->
            viewModel.updateArrayOfDayLessons(weekLessons) {
                binding.progressDownload.visibility = View.GONE
                binding.tabLayoutSchedule.visibility = View.VISIBLE
                binding.viewPagerSchedule.visibility = View.VISIBLE

                val adapter = LessonsTabsPagerAdapter(viewModel, childFragmentManager, lifecycle)
                binding.viewPagerSchedule.adapter = adapter
                binding.viewPagerSchedule.isUserInputEnabled = true
                TabLayoutMediator(binding.tabLayoutSchedule, binding.viewPagerSchedule) { tab, position ->
                    tab.text = when (position) {
                        0 -> resources.getString(R.string.monday)
                        1 -> resources.getString(R.string.tuesday)
                        2 -> resources.getString(R.string.wednesday)
                        3 -> resources.getString(R.string.thursday)
                        4 -> resources.getString(R.string.friday)
                        5 -> resources.getString(R.string.saturday)
                        else -> throw TabLayoutCanNotHaveMoreThan6TabsException()
                    }
                }.attach()
            }
        }

        binding.textGradeName.text = viewModel.grade.toString()

        binding.buttonChangeGrade.setOnClickListener {
            val shPreferences = requireActivity().getSharedPreferences(
                SH_PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
            shPreferences.edit().remove(SH_PREFERENCES_KEY_SCHOOL_ID)
                .remove(SH_PREFERENCES_KEY_SUBGROUP_ID).apply()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        binding.buttonCancel.setOnClickListener {
            viewModel.updateChosenNavViewItemId(R.id.menuItemMain)
        }

        return binding.root
    }

    class LessonsTabsPagerAdapter(
        private var viewModel: MainMenuViewModel,
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount() = 6

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0, 1, 2, 3, 4, 5 -> {
                    LessonsScheduleFragment(position)
                }
                else -> throw LessonOutOfBoundsException()
            }
        }

    }

}