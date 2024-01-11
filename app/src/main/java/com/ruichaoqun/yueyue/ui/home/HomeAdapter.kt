package com.ruichaoqun.yueyue.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import javax.inject.Inject


class HomeAdapter @Inject constructor():
    PagingDataAdapter<HomePageItemBean, HomeAdapter.HomeViewHolder>(HomeComparator) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    inner class HomeViewHolder(itemView: View) : ViewHolder(itemView)
}

object HomeComparator : DiffUtil.ItemCallback<HomePageItemBean>() {
    override fun areItemsTheSame(oldItem: HomePageItemBean, newItem: HomePageItemBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomePageItemBean, newItem: HomePageItemBean): Boolean {
        return oldItem == newItem
    }
}