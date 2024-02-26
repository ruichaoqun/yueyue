package com.ruichaoqun.yueyue.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.PublicNoArticleBean
import com.ruichaoqun.yueyue.databinding.ItemPublicNoBinding
import com.ruichaoqun.yueyue.ui.Web.WebActivity
import javax.inject.Inject


class PublicNoItemAdapter @Inject constructor():PagingDataAdapter<PublicNoArticleBean,PublicNoItemAdapter.ViewHolder>(SystemDataDiff) {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mBinding = ItemPublicNoBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.mBinding.tvTitle.text = title
            holder.mBinding.tvDate.text = niceDate
            holder.mBinding.root.setOnClickListener {
                WebActivity.startWebActivity(holder.mBinding.root.context,link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_public_no, parent, false)
        return ViewHolder(view)
    }
}

object SystemDataDiff: DiffUtil.ItemCallback<PublicNoArticleBean>(){
    override fun areItemsTheSame(oldItem: PublicNoArticleBean, newItem: PublicNoArticleBean): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PublicNoArticleBean, newItem: PublicNoArticleBean): Boolean {
        return oldItem.collect == newItem.collect
    }

}