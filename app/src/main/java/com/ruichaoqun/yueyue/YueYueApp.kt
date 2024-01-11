package com.ruichaoqun.yueyue

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YueYueApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}