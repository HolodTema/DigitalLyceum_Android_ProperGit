package com.example.lyceumapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lyceumapp.R
import com.example.lyceumapp.databinding.FragmentEventsBinding
import com.example.lyceumapp.viewmodel.MainMenuViewModel


class EventsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: MainMenuViewModel by activityViewModels()
        val binding = FragmentEventsBinding.inflate(inflater)

        binding.buttonToMainPage.setOnClickListener {
            viewModel.updateChosenNavViewItemId(R.id.menuItemMain)
        }
        return binding.root
    }
}