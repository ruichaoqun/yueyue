package com.ruichaoqun.yueyue.core.network.di

import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import com.ruichaoqun.yueyue.core.network.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun RetrofitDataSource.binds() : RemoteDataSource
}