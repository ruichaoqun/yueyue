package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.HomePageBean

interface RemoteDataSource {
    suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean>
}