package com.ruichaoqun.yueyue.core.repository.publickno

import com.ruichaoqun.yueyue.core.model.PageBean
import com.ruichaoqun.yueyue.core.model.PublicNo
import com.ruichaoqun.yueyue.core.model.PublicNoArticleBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface PublicNoRepository {
    fun getPublicNo(): Flow<NetWorkResponse<List<PublicNo>>>

    suspend fun getPublicNoArticleList(page:Int,id:Int,key:String ?= null):NetWorkResponse<PageBean<PublicNoArticleBean>>
}