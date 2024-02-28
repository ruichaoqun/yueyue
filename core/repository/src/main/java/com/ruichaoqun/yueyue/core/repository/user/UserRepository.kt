package com.ruichaoqun.yueyue.core.repository.user

import com.ruichaoqun.yueyue.core.network.NetWorkResponse

interface UserRepository {
    suspend fun login(username: String, password: String): NetWorkResponse<Any>

    suspend fun logout(): NetWorkResponse<Any>

    suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): NetWorkResponse<Any>
}