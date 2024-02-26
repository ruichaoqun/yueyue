package com.ruichaoqun.yueyue.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ruichaoqun.yueyue.core.repository.publickno.PublicNoPagingSource
import com.ruichaoqun.yueyue.core.repository.publickno.PublicNoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublicNoItemViewModel @Inject constructor(val publicNoRepository: PublicNoRepository):ViewModel() {
    var cid:Int = 0
    var key:String ?= null
    val page = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PublicNoPagingSource(publicNoRepository,cid,key) }
    ).flow.cachedIn(viewModelScope)
}