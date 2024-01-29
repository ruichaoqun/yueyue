package com.ruichaoqun.yueyue.core.repository.systemdata

import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SystemDataRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):SystemDataRepository {
    override fun getSystemData(): Flow<NetWorkResponse<List<SystemDataBean>>> =
        flow {
            emit(remoteDataSource.getSystemData())
        }

    override suspend fun getArticleList(
        page: Int,
        cid: Int
    ): NetWorkResponse<PageBean<ArticleItemBean>> =
        remoteDataSource.getArticleList(page,cid)

}