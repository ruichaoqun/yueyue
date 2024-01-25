package com.ruichaoqun.yueyue.core.repository.main

import android.util.Log
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource) :
    MainRepository {
    override fun getBanner(): Flow<NetWorkResponse<List<BannerItemBean>>> =
        flow {
            Log.e("AAAAA","getBanner")
            emit(remoteDataSource.getBanner())
        }

    override suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean> {
        return coroutineScope {
            if (page == 0) {
                val data = async { remoteDataSource.getHomeList(page) }
                val topics = async { remoteDataSource.getTopicList() }
                val pair = Pair(topics.await(), data.await())
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