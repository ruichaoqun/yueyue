package com.ruichaoqun.yueyue.core.network

import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.model.PublicNo
import com.ruichaoqun.yueyue.core.model.PublicNoArticleBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.model.UserBean
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

    override suspend fun getProject(): NetWorkResponse<List<ProjectItemBean>> =
        networkApi.getProject()


    override suspend fun getProjectList(page: Int,cid:Int): NetWorkResponse<PageBean<ProjectBean>> =
        networkApi.getProjectList(page,cid)

    override suspend fun getPublicNo(): NetWorkResponse<List<PublicNo>> =
        networkApi.getPublicNo()

    override suspend fun getPublicNoArticleList(page: Int, id: Int, key: String?):NetWorkResponse<PageBean<PublicNoArticleBean>> =
        networkApi.getPublicNoArticleList(page,id,key)

    override suspend fun login(username: String, password: String): NetWorkResponse<String> =
        networkApi.login(username,password)

    override suspend fun logout(): NetWorkResponse<String> =
        networkApi.logout()

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): NetWorkResponse<UserBean> =
        networkApi.register(username, password, repassword)
}