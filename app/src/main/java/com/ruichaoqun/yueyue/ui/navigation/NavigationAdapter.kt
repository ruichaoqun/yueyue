package com.ruichaoqun.yueyue.ui.navigation

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.model.NavigationChildBean
import com.ruichaoqun.yueyue.databinding.ItemNavigationHeaderBinding
import com.ruichaoqun.yueyue.databinding.ItemNavigationItemBinding
import com.ruichaoqun.yueyue.ui.Web.WebActivity
import kotlin.random.Random


class NavigationAdapter constructor(val mData:List<Any>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            HeadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_navigation_header,parent,false))
        } else {
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_navigation_item,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        val data = mData[position]
        if (data is NavigationBean)
            return 1
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as HeadViewHolder).convert(mData[position])
        } else {
            (holder as ItemViewHolder).convert(mData[position])
        }
    }

    class HeadViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val binding = ItemNavigationHeaderBinding.bind(itemView)

        fun convert(data: Any) {
            val item = data as NavigationBean
            binding.tvHeader.text = item.name
        }
    }

    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNavigationItemBinding.bind(itemView)
        fun convert(data: Any) {
            val item = data as NavigationChildBean
            binding.tvName.text = item.title
            binding.tvName.backgroundTintList = ColorStateList.valueOf(getRandomLightColors())
            binding.root.setOnClickListener {
                WebActivity.startWebActivity(itemView.context,item.link)
            }
        }

        private fun getRandomLightColors():Int{
            val minColor = 100
            val maxColor = 255
            val blue = Random.nextInt(minColor+1, maxColor-1)
            val red = Random.nextInt(blue, maxColor)
            val green = Random.nextInt(minColor, blue)
            return Color.rgb(red,green,blue)
        }
    }
}