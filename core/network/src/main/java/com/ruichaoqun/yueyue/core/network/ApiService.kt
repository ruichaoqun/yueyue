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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(value = "banner/json")
    suspend fun getBannerList(): NetWorkResponse<List<BannerItemBean>>

    @GET(value = "article/list/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): NetWorkResponse<PageBean<HomePageItemBean>>

    @GET(value = "article/top/json")
    suspend fun getTopList(): NetWorkResponse<List<HomePageItemBean>>

    /**
     * 体系数据
     */
    @GET(value = "tree/json")
    suspend fun getSystemData():NetWorkResponse<List<SystemDataBean>>

    /**
     * 知识体系下的文章
     */
    @GET(value = "article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int,@Query("cid") cid:Int): NetWorkResponse<PageBean<ArticleItemBean>>

    /**
     * 作者的文章
     */
    @GET(value = "article/list/{page}/json")
    suspend fun getArticleListByAuthor(@Path("page") page: Int,@Query("author") author:String): NetWorkResponse<PageBean<ArticleItemBean>>


    /**
     * 导航数据
     */
    @GET(value = "navi/json")
    suspend fun getNavigationList(): NetWorkResponse<List<NavigationBean>>

    /**
     * 项目
     */
    @GET(value = "project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int,@Query("cid") cid:Int):NetWorkResponse<PageBean<ProjectBean>>

    /**
     * 项目
     */
    @GET(value = "project/tree/json")
    suspend fun getProject():NetWorkResponse<List<ProjectItemBean>>


    /**
     * 公众号
     */
    @GET(value = "wxarticle/chapters/json")
    suspend fun getPublicNo():NetWorkResponse<List<PublicNo>>

    @GET(value = "wxarticle/list/{id}/{page}/json")
    suspend fun getPublicNoArticleList(@Path("page") page:Int,@Path("id") id:Int,@Query("k") key:String ?= null):NetWorkResponse<PageBean<PublicNoArticleBean>>

    /**
     * 登录
     */
    @POST(value = "user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String,@Field("password") password: String): NetWorkResponse<PageBean<ProjectBean>>

}