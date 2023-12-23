package com.example.yukigames.util

import android.content.SharedPreferences

class SessionManager(private val sharedPreferences: SharedPreferences) {

    fun getIsFirstRun() = sharedPreferences.getBoolean(Constants.FIRST_RUN_KEY, true)

    fun setIsFirstRun(value : Boolean){
        val editor = sharedPreferences.edit().putBoolean(Constants.FIRST_RUN_KEY, value).apply()
    }

}