package com.ruichaoqun.core.datastore

import com.ruichaoqun.yueyue.core.model.UserBean
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getUserInfo():Flow<UserBean>

    suspend fun saveUserInfo(userInfo:UserBean)
}