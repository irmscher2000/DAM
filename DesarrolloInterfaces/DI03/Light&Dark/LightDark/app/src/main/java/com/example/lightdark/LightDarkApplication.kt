package com.example.lightdark

import android.app.Application
import com.example.lightdark.settings.ThemeSetup

class LightDarkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ThemeSetup.applyTheme(this)
    }
}