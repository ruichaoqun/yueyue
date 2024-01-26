package com.ruichaoqun.yueyue.core.repository.main

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource) :
    MainRepository {
    override fun getBanner(): Flow<NetWorkResponse<List<BannerItemBean>>> =
        flow {
            emit(remoteDataSource.getBanner())
        }

    override suspend fun getHomeList(page: Int): NetWorkResponse<PageBean<HomePageItemBean>> {
        return coroutineScope {
            if (page == 0) {
                val data = async { remoteDataSource.getHomeList(page) }
                val topics = async { remoteDataSource.getTopicList() }
                val pair = Pair(topics.await(), data.await())
                pair.second.apply {
                    this.data.datas.addAll(0,pair.first.data.onEach { it.isTopic = true })
                }
            } else remoteDataSource.getHomeList(page)
        }
    }


    override suspend fun getTopicList(): NetWorkResponse<List<HomePageItemBean>> =
        remoteDataSource.getTopicList()
}