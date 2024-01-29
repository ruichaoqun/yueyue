package com.ruichaoqun.yueyue.widget.popup

import android.annotation.SuppressLint
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

class DropBoxPopupWindow<T>(context: Context,private val mData: MutableList<T>,val onItemCLick:(position:Int) -> Unit) : PopupWindow(context) {
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
                    isLastItemDecorated = false
                    dividerThickness = context.dpToPx(0.8f)
                },
            )
            adapter = SimpleAdapter(mData,onItemCLick)
        }
        contentView = view
    }

    public fun refreshData(data:MutableList<T>) {
        (recyclerView.adapter as SimpleAdapter<T>).setData(data)
    }

    class SimpleAdapter<T>(val mData:MutableList<T>,val onItemCLick:(position:Int) -> Unit) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
        private var selectPosition = 0
        @SuppressLint("NotifyDataSetChanged")
        fun setData(data:List<T>) {
            mData.clear()
            mData.addAll(data)
            selectPosition = 0
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.dpToPx(40f))
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(context.getColor(R.color.color_333))
                setPadding(context.dpToPx(16f),0,0,0)
            }
            return ViewHolder(textView)
        }

        override fun getItemCount(): Int = mData.size

        override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
            val bean = mData[position]
            (holder.itemView as TextView).apply {
                text = bean.toString()
                isSelected = selectPosition == position
                setOnClickListener {
                    selectPosition = position
                    onItemCLick(position)
                }
            }
        }

        class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    }
}