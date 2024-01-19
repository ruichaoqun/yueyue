package com.ruichaoqun.yueyue.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.databinding.ItemStateFootBinding

class CommonFootAdapter(private val retry: () -> Unit) :LoadStateAdapter<CommonFootAdapter.Holder>() {


    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.binding.groupLoadMore.isVisible = loadState is LoadState.Loading
        holder.binding.groupLoadError.isVisible = loadState is LoadState.Error
        holder.binding.groupLoadNoMore.isVisible = loadState.endOfPaginationReached
        holder.binding.btnError.setOnClickListener{retry}
    }


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder =
        Holder(ItemStateFootBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_state_foot,parent,false)))

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return super.displayLoadStateAsItem(loadState) ||
                (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }

    inner class Holder(val binding:ItemStateFootBinding):ViewHolder(binding.root)
}