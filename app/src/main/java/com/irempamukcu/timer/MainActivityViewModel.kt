package com.irempamukcu.timer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(): ViewModel() {
    private lateinit var timer: CountDownTimer
    private var second = MutableLiveData<Int>()
    private var finish = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Long>()


    fun seconds() : LiveData<Int>{return second}
    fun isFinished(): LiveData<Boolean>{return finish}

    fun startTimer(){
        timer = object : CountDownTimer(timerValue.value!!.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {


                val timeLeft = millisUntilFinished/1000
                second.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finish.value = true

            }

        }.start()


    }

    fun stopTimer(){
        timer.cancel()
    }

}