package com.ruichaoqun.yueyue.core.repository.user

import com.ruichaoqun.core.datastore.LocalDataSource
import com.ruichaoqun.yueyue.core.model.UserBean
import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource,val localDataSource: LocalDataSource):UserRepository {
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
    ): Flow<NetWorkResponse<UserBean>> =
        flow {
            emit(remoteDataSource.register(username,password, repassword))
        }

    override fun getUserInfo(): Flow<UserBean> = localDataSource.getUserInfo()

    override suspend fun saveUserInfo(userInfo: UserBean) {
        localDataSource.saveUserInfo(userInfo)
    }

}