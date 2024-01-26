package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {
    suspend fun getHomeList(page: Int): NetWorkResponse<PageBean<HomePageItemBean>>

    suspend fun getTopicList() :NetWorkResponse<List<HomePageItemBean>>

    suspend fun getBanner() :NetWorkResponse<List<BannerItemBean>>

    suspend fun getSystemData():NetWorkResponse<List<SystemDataBean>>

    suspend fun getArticleList(page: Int, cid:Int): NetWorkResponse<PageBean<ArticleItemBean>>

    suspend fun getArticleListByAuthor(page: Int,author:String): NetWorkResponse<PageBean<ArticleItemBean>>

    suspend fun getNavigationList(): NetWorkResponse<List<NavigationBean>>

    suspend fun getProjectItemList():NetWorkResponse<List<ProjectItemBean>>

    suspend fun getProjectList(page: Int,cid:Int): NetWorkResponse<PageBean<ProjectBean>>

}