package com.ruichaoqun.yueyue.core.repository.publickno

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.PublicNoArticleBean

class PublicNoPagingSource (private val publicNoRepository: PublicNoRepository,
                            private val cid:Int,private val key:String ?= null):PagingSource<Int,PublicNoArticleBean>() {

    override fun getRefreshKey(state: PagingState<Int, PublicNoArticleBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PublicNoArticleBean> {
        return try {
            val page = params.key ?: 0
            val data = publicNoRepository.getPublicNoArticleList(page,cid,key)
            if (data.errorCode != 0) {
                return LoadResult.Error(Throwable("网络错误"))
            }
            val nextKey = if (data.data.datas.size >= data.data.size) page.plus(1) else null
            LoadResult.Page(
                data = data.data.datas,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}