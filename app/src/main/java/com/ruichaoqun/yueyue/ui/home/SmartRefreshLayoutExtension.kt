package com.ruichaoqun.yueyue.ui.home

import android.util.Log
import com.scwang.smart.refresh.layout.SmartRefreshLayout

inline fun SmartRefreshLayout.setOnRefreshing(isRefresh: Boolean) {
    if (isRefresh) {
        Log.e("AAAA","autoRefreshAnimationOnly")
        autoRefreshAnimationOnly()
    } else {
        Log.e("AAAA","finishRefresh")
        finishRefresh()
    }
}