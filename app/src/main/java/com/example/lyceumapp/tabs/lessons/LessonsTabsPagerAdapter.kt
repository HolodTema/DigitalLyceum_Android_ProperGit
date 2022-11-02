package com.example.lyceumapp.tabs.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lyceumapp.Const
import com.example.lyceumapp.LessonOutOfBoundsException
import com.example.lyceumapp.RequestManager
import com.example.lyceumapp.database.LessonDB


class LessonsTabsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val lessonsForGrade: ArrayList<LessonDB>):
    FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount() = 6

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0, 1, 2, 3, 4, 5 -> {
                val fragment = LessonsScheduleFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(Const.BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_GRADE, RequestManager.getLessonsForOneDayOfGrade(lessonsForGrade, position))
                fragment.arguments = bundle
                fragment
            }
            else -> throw LessonOutOfBoundsException()
        }
    }

}