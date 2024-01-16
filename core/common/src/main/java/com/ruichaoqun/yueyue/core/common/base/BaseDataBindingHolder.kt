package com.ruichaoqun.yueyue.core.common.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseDataBindingHolder<BD : ViewDataBinding>(view:View) : RecyclerView.ViewHolder(view){
    val viewBinding = DataBindingUtil.bind<BD>(view)
}