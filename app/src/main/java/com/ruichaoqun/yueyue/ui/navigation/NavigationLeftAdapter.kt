package com.ruichaoqun.yueyue.ui.navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.databinding.ItemNavigationLeftBinding

class NavigationLeftAdapter(
    val mDatas: List<NavigationBean>,
    val onItemClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<NavigationLeftAdapter.ViewHolder>() {
    var mSelectPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_navigation_left, parent, false)
        )
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.apply {
            text = mDatas[position].name
            isSelected = mSelectPosition == position
            setOnClickListener {
                refreshSelectState(position)
                onItemClickListener(position)
            }
        }
    }

    fun refreshSelectState(position: Int) {
        var oldPosition = mSelectPosition
        mSelectPosition = position
        notifyItemChanged(mSelectPosition)
        notifyItemChanged(oldPosition)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemNavigationLeftBinding.bind(itemView)
    }
}