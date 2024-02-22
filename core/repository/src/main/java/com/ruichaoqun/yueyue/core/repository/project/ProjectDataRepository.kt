package com.ruichaoqun.yueyue.core.repository.project

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse

interface ProjectDataRepository {
    suspend fun getProjectList(page: Int): NetWorkResponse<PageBean<ProjectBean>>
}