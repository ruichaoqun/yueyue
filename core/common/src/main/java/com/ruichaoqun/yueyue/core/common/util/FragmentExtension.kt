package com.ruichaoqun.yueyue.core.common.util

import androidx.fragment.app.Fragment

inline fun Fragment.dpToPx(dp:Float) :Int = (dp * resources.displayMetrics.density).toInt()
