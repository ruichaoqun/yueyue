package com.ruichaoqun.yueyue.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.model.BannerItemBean
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.main.MainPagingSource
import com.ruichaoqun.yueyue.core.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    val homeUiState:StateFlow<HomeBannerUiState> = homeBannerUiState(mainRepository)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeBannerUiState.Loading
        )


    val pageFlow = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MainPagingSource(mainRepository) }
    ).flow.cachedIn(viewModelScope)

    private fun homeBannerUiState(repository: MainRepository) : Flow<HomeBannerUiState> {
        return repository.getBanner().asResult()
            .map { result ->
                when(result) {
                    is Result.Success -> {
                        HomeBannerUiState.Success(result.data)
                    }

                    is Result.Loading -> {
                        HomeBannerUiState.Loading
                    }

                    is Result.Error -> {
                        HomeBannerUiState.Error
                    }
                }
            }
    }
}

sealed interface HomeBannerUiState {
    object Loading : HomeBannerUiState
    object Error : HomeBannerUiState

    data class Success(val banners: List<BannerItemBean>):HomeBannerUiState
}