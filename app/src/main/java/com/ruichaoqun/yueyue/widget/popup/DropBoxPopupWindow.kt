package com.ruichaoqun.yueyue.widget.popup

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.util.dpToPx
import com.ruichaoqun.yueyue.core.model.SimpleSelect

class DropBoxPopupWindow<T:SimpleSelect>(context: Context,var mData: List<T>,val onItemCLick:(position:Int) -> Unit) : PopupWindow(context) {
    private lateinit var recyclerView:RecyclerView
    init {
        initView(context)
    }

    private fun initView(context:Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view,null)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                MaterialDividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    isLastItemDecorated = true
                    dividerThickness = context.dpToPx(0.8f)
                },
            )
            adapter = SimpleAdapter<T>{position ->
                onItemCLick(position)
                dismiss()
            }.apply {
                setData(mData)
            }
        }
        contentView = view
        width = WindowManager.LayoutParams.MATCH_PARENT
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isFocusable = true
        isOutsideTouchable = true
    }

    public fun refreshData(data:MutableList<T>) {
        mData = data
        data.forEachIndexed { index, t ->
            t.isSelect = index == 0
        }
        (recyclerView.adapter as SimpleAdapter<T>).setData(data)
    }

    class SimpleAdapter<T:SimpleSelect>(val onItemCLick:(position:Int) -> Unit) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
        private val mData:MutableList<T> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        fun setData(data:List<T>) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.dpToPx(40f))
                gravity = Gravity.CENTER
                setTextColor(context.getColor(R.color.color_item_select))
                setPadding(context.dpToPx(16f),0,0,0)
            }
            return ViewHolder(textView)
        }

        override fun getItemCount(): Int = mData.size

        override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
            val bean = mData[position]
            (holder.itemView as TextView).apply {
                text = bean.toString()
                isSelected = bean.isSelect
                setOnClickListener {
                    setSelect(position)
                    onItemCLick(position)
                }
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun setSelect(position: Int) {
            mData.forEachIndexed { index, t ->
                t.isSelect = index == position
            }
            notifyDataSetChanged()
        }

        class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    }
}