package com.ruichaoqun.yueyue.core.repository.di

import com.ruichaoqun.yueyue.core.network.RemoteDataSource
import com.ruichaoqun.yueyue.core.repository.main.MainPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingSourceModule {

    @Singleton
    @Provides
    fun provideMainPagingSource(remoteDataSource: RemoteDataSource) = MainPagingSource(remoteDataSource)
}