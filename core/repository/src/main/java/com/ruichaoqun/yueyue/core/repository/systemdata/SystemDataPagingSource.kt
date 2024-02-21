package com.ruichaoqun.yueyue.core.repository.systemdata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.ArticleItemBean

class SystemDataPagingSource constructor(val systemDataRepository: SystemDataRepository,val cid:Int):PagingSource<Int, ArticleItemBean>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleItemBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItemBean> {
        return try {
            val page = params.key ?: 0
            val data = systemDataRepository.getArticleList(page,cid)
            if (data.errorCode != 0) {
                return LoadResult.Error(Throwable("网络错误"))
            }
            val nextKey = if (data.data.datas.size >= 20) page.plus(1) else null
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