package com.ruichaoqun.yueyue.core.common.util

import android.content.Context
import android.widget.Toast

inline fun Context.dpToPx(dp:Float) :Int = (dp * resources.displayMetrics.density).toInt()

inline fun Context.toast(text:CharSequence, duration:Int = Toast.LENGTH_SHORT) = Toast.makeText(this,text,Toast.LENGTH_SHORT).show()

inline fun Context.toast(stringRes:Int) = Toast.makeText(this,getString(stringRes),Toast.LENGTH_SHORT).show()