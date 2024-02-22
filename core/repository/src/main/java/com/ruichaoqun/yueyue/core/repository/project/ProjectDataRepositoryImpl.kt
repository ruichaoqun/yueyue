package com.ruichaoqun.yueyue.core.repository.project

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import javax.inject.Inject

class ProjectDataRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):ProjectDataRepository {
    override suspend fun getProjectList(page: Int): NetWorkResponse<PageBean<ProjectBean>> =
        remoteDataSource.getProjectList(page)

}