package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDataSource @Inject constructor(private val networkApi: ApiService) :
    RemoteDataSource {

    override suspend fun getHomeList(page: Int): NetWorkResponse<PageBean<HomePageItemBean>> =
        networkApi.getHomeList(page)

    override suspend fun getTopicList(): NetWorkResponse<List<HomePageItemBean>> =
        networkApi.getTopList()

    override suspend fun getBanner(): NetWorkResponse<List<BannerItemBean>> =
        networkApi.getBannerList()

    override suspend fun getSystemData(): NetWorkResponse<List<SystemDataBean>> =
        networkApi.getSystemData()

    override suspend fun getArticleList(
        page: Int,
        cid: Int
    ): NetWorkResponse<PageBean<ArticleItemBean>> =
        networkApi.getArticleList(page, cid)

    override suspend fun getArticleListByAuthor(
        page: Int,
        author: String
    ): NetWorkResponse<PageBean<ArticleItemBean>> =
        networkApi.getArticleListByAuthor(page, author)

    override suspend fun getNavigationList(): NetWorkResponse<List<NavigationBean>> =
        networkApi.getNavigationList()

    override suspend fun getProjectList(page: Int): NetWorkResponse<PageBean<ProjectBean>> =
        networkApi.getProjectList(page)
}