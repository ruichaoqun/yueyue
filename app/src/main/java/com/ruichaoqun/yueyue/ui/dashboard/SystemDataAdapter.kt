package com.ruichaoqun.yueyue.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.util.formatDate
import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.databinding.ItemSystemArticleBinding
import com.ruichaoqun.yueyue.ui.Web.WebActivity
import javax.inject.Inject


class SystemDataAdapter @Inject constructor():PagingDataAdapter<ArticleItemBean,SystemDataAdapter.ViewHolder>(SystemDataDiff) {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val mBinding = ItemSystemArticleBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.mBinding.tvAuthor.text = if (author.isNullOrEmpty()) shareUser else author
            holder.mBinding.tvTitle.text = title
            holder.mBinding.tvTags.text = "$superChapterName/$chapterName"
            holder.mBinding.tvDate.text = publishTime.formatDate()
            holder.mBinding.root.setOnClickListener {
                WebActivity.startWebActivity(holder.mBinding.root.context,link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_system_article, parent, false)
        return ViewHolder(view)
    }
}

object SystemDataDiff:DiffUtil.ItemCallback<ArticleItemBean>(){
    override fun areItemsTheSame(oldItem: ArticleItemBean, newItem: ArticleItemBean): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleItemBean, newItem: ArticleItemBean): Boolean {
        return oldItem.collect == newItem.collect
    }

}