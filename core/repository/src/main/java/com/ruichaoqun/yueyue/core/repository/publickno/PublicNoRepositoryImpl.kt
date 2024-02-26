package com.ruichaoqun.yueyue.core.repository.publickno

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.PublicNo
import com.ruichaoqun.yueyue.core.model.PublicNoArticleBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PublicNoRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):PublicNoRepository {
    override fun getPublicNo(): Flow<NetWorkResponse<List<PublicNo>>> =
        flow {
            emit(remoteDataSource.getPublicNo())
        }

    override suspend fun getPublicNoArticleList(page: Int, id: Int, key: String?):NetWorkResponse<PageBean<PublicNoArticleBean>> =
        remoteDataSource.getPublicNoArticleList(page,id, key)
}