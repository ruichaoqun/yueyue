package com.ruichaoqun.yueyue.core.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

inline fun Long?.formatDate():String {
    if (this == null) return ""
    val currentTime = System.currentTimeMillis()
    val elapsedTime = currentTime - this
    return when {
        elapsedTime < TimeUnit.DAYS.toMillis(1) -> {
            val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
            "$hours 小时前"
        }
        elapsedTime < TimeUnit.DAYS.toMillis(3) -> {
            val days = TimeUnit.MILLISECONDS.toDays(elapsedTime)
            "$days 天前"
        }
        else -> {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = Date(this)
            sdf.format(date)
        }
    }
}