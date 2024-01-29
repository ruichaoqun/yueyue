package com.ruichaoqun.yueyue.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.model.ArticleItemBean
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.model.SystemDataBean
import com.ruichaoqun.yueyue.core.model.SystemDataChildBean
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.main.MainPagingSource
import com.ruichaoqun.yueyue.core.repository.systemdata.SystemDataPagingSource
import com.ruichaoqun.yueyue.core.repository.systemdata.SystemDataRepository
import com.ruichaoqun.yueyue.ui.home.HomeBannerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DashboardViewModel constructor(val systemDataRepository: SystemDataRepository): ViewModel() {
    private var cid:Int = 0

    val flow :Flow<String> = flowOf()

    val systemTagUiState : StateFlow<SystemTagUiState> = systemTagUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SystemTagUiState.Loading
        )

    private lateinit var pagingDataSource :SystemDataPagingSource

    val page = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { pagingDataSource }
    ).flow.cachedIn(viewModelScope)


    private fun systemTagUiState():Flow<SystemTagUiState> =
        systemDataRepository.getSystemData().asResult()
            .map {
                when(it){
                    is Result.Error -> SystemTagUiState.Error
                    Result.Loading -> SystemTagUiState.Loading
                    is Result.Success -> {
                        val firstTag = it.data[0]
                        val secondTag = firstTag.children[0]
                        pagingDataSource = SystemDataPagingSource(systemDataRepository,secondTag.id)
                        SystemTagUiState.Success(it.data)
                    }
                }
            }

    fun selectFirstTag(tag:SystemDataBean) {
        cid
    }

    fun selectSecondTag(tag:SystemDataChildBean) {

    }
}

sealed interface SystemTagUiState {
    object Loading : SystemTagUiState
    object Error : SystemTagUiState

    data class Success(val systemDatas: MutableList<SystemDataBean>): SystemTagUiState
}

sealed interface SystemListUiState{
    object Loading : SystemListUiState
    object Error : SystemListUiState

    data class Success(val list: List<ArticleItemBean>): SystemListUiState
}