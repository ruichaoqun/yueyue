package com.ruichaoqun.yueyue.core.repository.user

import com.ruichaoqun.yueyue.core.model.UserBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(username: String, password: String): NetWorkResponse<String>

    suspend fun logout(): NetWorkResponse<String>

    suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): Flow<NetWorkResponse<UserBean>>

    fun getUserInfo():Flow<UserBean>

    suspend fun saveUserInfo(userInfo:UserBean)
}