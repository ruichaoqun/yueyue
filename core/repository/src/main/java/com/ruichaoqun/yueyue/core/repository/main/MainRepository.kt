package com.ruichaoqun.yueyue.core.repository.main

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getBanner() : Flow<NetWorkResponse<List<BannerItemBean>>>

    suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean>

    suspend fun getTopicList() : NetWorkResponse<List<HomePageItemBean>>
}