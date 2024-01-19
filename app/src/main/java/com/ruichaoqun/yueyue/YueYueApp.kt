package com.ruichaoqun.yueyue

import android.app.Application
import android.content.Context
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YueYueApp:Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> MaterialHeader(context) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}