package com.ruichaoqun.yueyue.core.repository

import com.ruichaoqun.yueyue.core.network.NetWorkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Error(val errorMsg: String? = "", val errorCode: Int? = null) : Result<Nothing>

    object Loading : Result<Nothing>
}

fun <T> Flow<NetWorkResponse<T>>.asResult(): Flow<Result<T>> {
    return this.map {
        if (it.errorCode == 0) Result.Success(it.data) else Result.Error(it.errorMsg, it.errorCode)
    }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error()) }
}