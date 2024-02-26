package com.ruichaoqun.yueyue.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.model.ProjectBean
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataPagingSource
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepository
import com.ruichaoqun.yueyue.core.repository.systemdata.SystemDataPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectItemViewModel @Inject constructor(private val projectDataRepository: ProjectDataRepository):ViewModel() {
    var cid:Int = 0
    val page = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { ProjectDataPagingSource(projectDataRepository,cid) }
    ).flow.cachedIn(viewModelScope)
}