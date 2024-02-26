package com.ruichaoqun.yueyue.core.repository.project

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProjectDataRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):ProjectDataRepository {
    override fun getProject(): Flow<NetWorkResponse<List<ProjectItemBean>>> =
        flow {
            emit(remoteDataSource.getProject())
        }

    override suspend fun getProjectList(page: Int,cid:Int): NetWorkResponse<PageBean<ProjectBean>> =
        remoteDataSource.getProjectList(page,cid)

}