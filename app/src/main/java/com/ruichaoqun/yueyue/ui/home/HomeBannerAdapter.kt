package com.ruichaoqun.yueyue.ui.home

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.ui.Web.WebActivity
import com.ruichaoqun.yueyue.ui.navigation.NavigationActivity
import com.youth.banner.adapter.BannerAdapter
import javax.inject.Inject

class HomeBannerAdapter @Inject constructor(): BannerAdapter<BannerItemBean, HomeBannerAdapter.ViewHolder>(null) {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ViewHolder(imageView)
    }

    override fun onBindView(holder: ViewHolder, data: BannerItemBean, position: Int, size: Int) {
        (holder.itemView as ImageView).load(data.imagePath){
            placeholder(R.drawable.placeholder_banner)
            error(R.drawable.placeholder_banner)
        }
        holder.itemView.setOnClickListener {
            WebActivity.startWebActivity(holder.itemView.context,data.url)
        }
    }
}