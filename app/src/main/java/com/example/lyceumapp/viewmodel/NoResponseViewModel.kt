package com.example.lyceumapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lyceumapp.Const

class NoResponseViewModel(application: Application): AndroidViewModel(application) {
    var amountAttemptsToConnect = 1
    var noResponseType = Const.NoResponseType.GetSchools
    val liveDataCountDownTimerSeconds = MutableLiveData<Int?>()

    init{
        if(amountAttemptsToConnect>=Const.AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING) {
            object: CountDownTimer(Const.DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT.toLong(), 1000) {
                override fun onTick(p0: Long) {
                    liveDataCountDownTimerSeconds.value = (p0/1000).toInt()
                }

                override fun onFinish() {
                    liveDataCountDownTimerSeconds.value = null //null implies that timer has finished
                }
            }.start()
        }
    }



}