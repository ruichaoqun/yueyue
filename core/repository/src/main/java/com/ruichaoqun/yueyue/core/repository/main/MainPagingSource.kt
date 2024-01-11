package com.ruichaoqun.yueyue.core.repository.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import javax.inject.Inject


class MainPagingSource @Inject constructor(private val remoteDataSource: RemoteDataSource):PagingSource<Int, HomePageItemBean>() {

    override fun getRefreshKey(state: PagingState<Int, HomePageItemBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomePageItemBean> {
        return try {
            val nextPageNumber = params.key ?: 0
            val data = remoteDataSource.getHomeList(nextPageNumber)
            LoadResult.Page(
                data = data.data.datas,
                prevKey = null,
                nextKey = data.data.curPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}