package com.ruichaoqun.yueyue.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.common.util.formatDate
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.databinding.ItemProjectBinding
import com.ruichaoqun.yueyue.databinding.ItemSystemArticleBinding
import com.ruichaoqun.yueyue.ui.Web.WebActivity
import javax.inject.Inject

class ProjectAdapter @Inject constructor():PagingDataAdapter<ProjectBean,ProjectAdapter.ViewHolder>(SystemDataDiff) {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mBinding = ItemProjectBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.mBinding.tvTitle.text = title
            holder.mBinding.tvDesc.text = desc
            holder.mBinding.tvDate.text = publishTime.formatDate()
            holder.mBinding.tvAuthor.text = author.ifEmpty { shareUser }
            holder.mBinding.ivLike.load(envelopePic){
                placeholder(R.mipmap.default_project_img)
                error(R.mipmap.default_project_img)
            }
            holder.mBinding.root.setOnClickListener {
                WebActivity.startWebActivity(holder.mBinding.root.context,link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }
}

object SystemDataDiff: DiffUtil.ItemCallback<ProjectBean>(){
    override fun areItemsTheSame(oldItem: ProjectBean, newItem: ProjectBean): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProjectBean, newItem: ProjectBean): Boolean {
        return oldItem.collect == newItem.collect
    }

}