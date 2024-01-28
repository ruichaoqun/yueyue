package com.ruichaoqun.yueyue.widget.popup

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.util.dpToPx

class DropBoxPopupWindow<T> : PopupWindow {
    private val mData:List<T>

    constructor(context: Context,data: List<T>) : super(context) {
        mData = data
        initView(context)

    }

    private fun initView(context:Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view,null)
        val recyclerView:RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = false
                    dividerThickness = context.dpToPx(0.8f)
                },
            )
            adapter =
        }
        contentView = view
    }

    class SimpleAdapter<T> : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
        var data:List<>

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleAdapter.ViewHolder {
            val textView = TextView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.dpToPx(40f))
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(context.getColor(R.color.color_333))
                setPadding(context.dpToPx(16f),0,0,0)
            }
            return ViewHolder(textView)
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: SimpleAdapter.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    }
}