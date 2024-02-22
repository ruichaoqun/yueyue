package com.ruichaoqun.yueyue.core.repository.di

import com.ruichaoqun.yueyue.core.repository.main.MainRepository
import com.ruichaoqun.yueyue.core.repository.main.MainRepositoryImpl
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepository
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepositoryImpl
import com.ruichaoqun.yueyue.core.repository.systemdata.SystemDataRepository
import com.ruichaoqun.yueyue.core.repository.systemdata.SystemDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMainRepository(mainRepository: MainRepositoryImpl) : MainRepository

    @Binds
    fun bindSystemDataRepository(systemDataRepository: SystemDataRepositoryImpl) : SystemDataRepository

    @Binds
    fun bindMainRepository(projectDataRepositoryImpl: ProjectDataRepositoryImpl) : ProjectDataRepository
}