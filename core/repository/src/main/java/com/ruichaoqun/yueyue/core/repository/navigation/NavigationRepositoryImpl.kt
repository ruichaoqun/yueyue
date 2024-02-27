package com.ruichaoqun.yueyue.core.repository.navigation

import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):NavigationRepository {
    override fun getNavigationList(): Flow<NetWorkResponse<List<NavigationBean>>> =
        flow {
            emit(remoteDataSource.getNavigationList())
        }
}