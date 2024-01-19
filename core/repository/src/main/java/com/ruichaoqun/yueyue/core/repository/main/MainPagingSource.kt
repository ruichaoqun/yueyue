package com.ruichaoqun.yueyue.core.repository.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.HomePageItemBean
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.delay
import javax.inject.Inject


class MainPagingSource @Inject constructor(private val mainRepository: MainRepository) :
    PagingSource<Int, HomePageItemBean>() {

    override fun getRefreshKey(state: PagingState<Int, HomePageItemBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomePageItemBean> {
        return try {
            val page = params.key?:0
            val data = mainRepository.getHomeList(page)
            if (data.errorCode != 0) {
                return LoadResult.Error(Throwable("网络错误"))
            }
            delay(3000)
            LoadResult.Page(
                data = data.data.datas?: mutableListOf(),
                prevKey = null,
                nextKey = if (page == 4) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}