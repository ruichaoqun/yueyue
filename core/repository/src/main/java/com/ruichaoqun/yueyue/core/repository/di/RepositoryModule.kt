package com.ruichaoqun.yueyue.core.repository.di

import com.ruichaoqun.yueyue.core.repository.main.MainRepository
import com.ruichaoqun.yueyue.core.repository.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun MainRepositoryImpl.binds() : MainRepository
}