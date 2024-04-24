package com.ruichaoqun.yueyue.core.repository.user

import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):UserRepository {
    override suspend fun login(username: String, password: String): NetWorkResponse<String> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): NetWorkResponse<String> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): Flow<NetWorkResponse<String>> =
        flow {
            emit(remoteDataSource.register(username,password, repassword))
        }

}