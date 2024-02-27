package com.ruichaoqun.yueyue.core.repository.navigation

import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
     fun getNavigationList(): Flow<NetWorkResponse<List<NavigationBean>>>
}