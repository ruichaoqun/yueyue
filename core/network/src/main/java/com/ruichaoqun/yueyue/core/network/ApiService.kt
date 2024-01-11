package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.HomePageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(value = "banner/json")
    suspend fun getBannerList(): NetWorkResponse<List<BannerItemBean>>

    @GET(value = "article/list/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): NetWorkResponse<HomePageBean>

    @GET(value = "article/top/json")
    suspend fun getTopList(): NetWorkResponse<List<HomePageItemBean>>
}