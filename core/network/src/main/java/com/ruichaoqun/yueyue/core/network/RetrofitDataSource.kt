package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDataSource @Inject constructor(private val networkApi:ApiService):RemoteDataSource {

    override suspend fun getHomeList(page: Int): NetWorkResponse<HomePageBean> =
        networkApi.getHomeList(page)

    override suspend fun getTopicList(): NetWorkResponse<List<HomePageItemBean>> =
        networkApi.getTopList()

    override suspend fun getBanner(): NetWorkResponse<List<BannerItemBean>> =
        networkApi.getBannerList()
}