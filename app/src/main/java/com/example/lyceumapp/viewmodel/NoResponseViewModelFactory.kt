package com.example.lyceumapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoResponseViewModelFactory(private val application: Application, private val amountAttemptsToConnect: Int):
ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoResponseViewModel(application, amountAttemptsToConnect) as T
    }
}