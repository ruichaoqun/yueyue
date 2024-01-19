package com.ruichaoqun.yueyue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.repository.main.MainPagingSource
import com.ruichaoqun.yueyue.core.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val mainRepository : MainRepository): ViewModel() {
    val pageFlow = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { MainPagingSource(mainRepository) }
    ) .flow.cachedIn(viewModelScope)



    fun dispatchIntent(mainIntent: MainIntent) {
        when(mainIntent) {
            is MainIntent.RefreshIntent -> {
                mainRepository.getBanner()
            }
        }
    }

}

sealed interface MainIntent{
    object RefreshIntent:MainIntent
}