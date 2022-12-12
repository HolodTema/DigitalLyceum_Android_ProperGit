package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyceumapp.json.subgroups.SubgroupInfoJson

class MainMenuViewModelFactory(private val application: Application, private val subgroupInfo: SubgroupInfoJson):
ViewModelProvider.AndroidViewModelFactory(application){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainMenuViewModel(application, subgroupInfo) as T
    }

}