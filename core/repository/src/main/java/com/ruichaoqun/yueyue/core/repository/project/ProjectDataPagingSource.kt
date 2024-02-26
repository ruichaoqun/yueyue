package com.ruichaoqun.yueyue.core.repository.project

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.repository.main.MainRepository

class ProjectDataPagingSource constructor(private val projectDataRepository: ProjectDataRepository,
                                          private val cid:Int):PagingSource<Int, ProjectBean>() {
    override fun getRefreshKey(state: PagingState<Int, ProjectBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectBean> {
        return try {
            val page = params.key ?: 0
            val data = projectDataRepository.getProjectList(page,cid)
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