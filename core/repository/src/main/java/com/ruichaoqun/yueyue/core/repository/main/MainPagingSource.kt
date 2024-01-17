package com.ruichaoqun.yueyue.core.repository.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import javax.inject.Inject


class MainPagingSource @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    PagingSource<Int, HomePageItemBean>() {

    override fun getRefreshKey(state: PagingState<Int, HomePageItemBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomePageItemBean> {
        return try {
            val data = params.key?.let {
                remoteDataSource.getHomeList(it).data.datas
            } ?: remoteDataSource.getTopicList().data.onEach { it.isTopic = true }
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = params.key?.let { it + 1 } ?: 0
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}