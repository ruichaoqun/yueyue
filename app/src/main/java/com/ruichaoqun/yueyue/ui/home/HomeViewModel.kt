package com.ruichaoqun.yueyue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.repository.main.MainPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(pagingSource:MainPagingSource): ViewModel() {

    val pageFlow = Pager(
        PagingConfig(pageSize = 20, enablePlaceholders = false), pagingSourceFactory = { pagingSource }
    ) .flow.cachedIn(viewModelScope)

}