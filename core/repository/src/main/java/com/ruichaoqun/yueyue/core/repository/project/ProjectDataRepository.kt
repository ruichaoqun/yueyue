package com.ruichaoqun.yueyue.core.repository.project

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface ProjectDataRepository {
    fun getProject(): Flow<NetWorkResponse<List<ProjectItemBean>>>

    suspend fun getProjectList(page: Int, cid: Int): NetWorkResponse<PageBean<ProjectBean>>
}