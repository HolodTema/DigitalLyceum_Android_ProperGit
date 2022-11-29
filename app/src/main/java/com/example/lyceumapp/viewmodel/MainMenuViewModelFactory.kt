package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainMenuViewModelFactory(private val application: Application):
ViewModelProvider.AndroidViewModelFactory(application){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainMenuViewModel(application) as T
    }

}