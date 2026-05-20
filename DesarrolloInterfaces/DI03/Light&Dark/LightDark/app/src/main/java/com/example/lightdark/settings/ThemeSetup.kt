package com.example.lightdark.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

object ThemeSetup {
    fun applyTheme(mode: String?, context: Context) {
        if ( "Oscuro" == mode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else if ("Claro" == mode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun applyTheme(context: Context){
        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val value = defaultSharedPreferences.getString(
            "theme",
            "Predeterminado"
        )
        applyTheme(value, context)
    }
}