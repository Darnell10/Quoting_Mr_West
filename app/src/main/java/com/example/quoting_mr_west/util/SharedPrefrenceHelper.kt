package com.example.quoting_mr_west.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPrefrenceHelper {

    companion object {

        private const val PREF_TIME = "Pref time"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedPrefrenceHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPrefrenceHelper =
            instance ?: synchronized(LOCK) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

        private fun buildHelper(context: Context): SharedPrefrenceHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefrenceHelper()
        }
    }

    fun savedUpdateTime(time: Long) {
        prefs?.edit(true) {
            putLong(PREF_TIME, time)
        }
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME, 0)

}