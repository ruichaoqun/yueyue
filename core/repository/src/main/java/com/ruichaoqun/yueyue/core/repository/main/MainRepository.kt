package com.ruichaoqun.yueyue.core.repository.main

import com.ruichaoqun.yueyue.core.model.BannerItemBean
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getBanner() : Flow<List<BannerItemBean>>
}