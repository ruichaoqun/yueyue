package com.ruichaoqun.yueyue.core.network

data class NetWorkResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)

sealed interface Result {
    data class Success<T>(val data: T) : Result

    data class Error(val errorMsg: String, val errorCode: Int? = null) : Result
}

inline fun <T> NetWorkResponse<T>.toResult(): Result =
    if (errorCode == 0) {
        Result.Success(data)
    } else Result.Error(errorMsg, errorCode)