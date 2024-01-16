package com.ruichaoqun.yueyue.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.base.BaseDataBindingHolder
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.databinding.ItemHomeBinding
import javax.inject.Inject


class HomeAdapter @Inject constructor():
    PagingDataAdapter<HomePageItemBean, BaseDataBindingHolder<ItemHomeBinding>>(HomeComparator) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.mTvId.text =  getItem(position)?.id?.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    inner class HomeViewHolder(itemView: View) : ViewHolder(itemView) {
        val mTvId :TextView = itemView.findViewById(R.id.tv_id)
    }
}

object HomeComparator : DiffUtil.ItemCallback<HomePageItemBean>() {
    override fun areItemsTheSame(oldItem: HomePageItemBean, newItem: HomePageItemBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomePageItemBean, newItem: HomePageItemBean): Boolean {
        return oldItem == newItem
    }
}