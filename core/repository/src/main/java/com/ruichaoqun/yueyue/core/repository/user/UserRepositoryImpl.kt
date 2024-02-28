package com.ruichaoqun.yueyue.core.repository.user

import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource

class UserRepositoryImpl constructor(remoteDataSource: RemoteDataSource):UserRepository {
    override suspend fun login(username: String, password: String): NetWorkResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): NetWorkResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): NetWorkResponse<Any> {
        TODO("Not yet implemented")
    }
}