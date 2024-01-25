package com.ruichaoqun.yueyue.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

data class NetWorkResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)