package com.ruichaoqun.yueyue.core.repository.main

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource) : MainRepository {
    override fun getBanner(): Flow<List<BannerItemBean>> {
        return flow {  }
    }

    override suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean> {
        return coroutineScope {
            if (page == 0) {
                val data = async { remoteDataSource.getHomeList(page) }
                val topics =  async { remoteDataSource.getTopicList()}
                val pair = Pair(topics.await(),data.await())
                pair.second.data.datas = (pair.second.data.datas ?: mutableListOf()).apply {
                    addAll(pair.first.data)
                }
                pair.second
            } else remoteDataSource.getHomeList(page)
        }
    }


    override suspend fun getTopicList(): NetWorkResponse<List<HomePageItemBean>> =
        remoteDataSource.getTopicList()
}