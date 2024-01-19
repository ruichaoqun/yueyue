package com.ruichaoqun.yueyue.ui.home

import com.scwang.smart.refresh.layout.SmartRefreshLayout

inline fun SmartRefreshLayout.setOnRefreshing(isRefresh: Boolean) {
    if (isRefresh) {
        autoRefreshAnimationOnly()
    } else {
        finishRefresh()
    }
}