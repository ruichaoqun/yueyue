package com.ruichaoqun.yueyue.core.repository.systemdata

import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface SystemDataRepository {
    fun getSystemData(): Flow<NetWorkResponse<List<SystemDataBean>>>

    suspend fun getArticleList(page: Int, cid:Int): NetWorkResponse<PageBean<ArticleItemBean>>
}