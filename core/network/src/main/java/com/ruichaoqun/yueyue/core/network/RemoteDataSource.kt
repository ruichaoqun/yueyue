package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean

interface RemoteDataSource {
    suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean>

    suspend fun getTopicList() :NetWorkResponse<List<HomePageItemBean>>

    suspend fun getBanner() :NetWorkResponse<List<BannerItemBean>>
}