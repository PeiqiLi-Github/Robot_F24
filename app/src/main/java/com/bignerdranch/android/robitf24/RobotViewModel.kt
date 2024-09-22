package com.bignerdranch.android.robitf24

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"

class RobotViewModel : ViewModel() {

    private var turnCount = 0

    val currentTurn: Int
        get() = turnCount

    init {
        Log.d(TAG, "ViewModel has been initialized" )//V I D E W
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel has been destroyed")
    }

    fun advanceTurn (){
        turnCount++

        if (turnCount > 3) {
            turnCount = 1
        }
        Log.d(TAG, "Turn advanced to $turnCount")
        
    }

}

