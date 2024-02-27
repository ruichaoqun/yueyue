package com.ruichaoqun.yueyue.core.repository.di

import com.ruichaoqun.yueyue.core.repository.main.MainRepository
import com.ruichaoqun.yueyue.core.repository.main.MainRepositoryImpl
import com.ruichaoqun.yueyue.core.repository.navigation.NavigationRepository
import com.ruichaoqun.yueyue.core.repository.navigation.NavigationRepositoryImpl
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepository
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepositoryImpl
import com.ruichaoqun.yueyue.core.repository.publickno.PublicNoRepository
import com.ruichaoqun.yueyue.core.repository.publickno.PublicNoRepositoryImpl
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
    fun bindProjectDataRepository(projectDataRepositoryImpl: ProjectDataRepositoryImpl) : ProjectDataRepository
    @Binds
    fun bindPublicNoRepository(publicNoRepositoryImpl: PublicNoRepositoryImpl) : PublicNoRepository

    @Binds
    fun bindNavigationRepository(navigationRepositoryImpl: NavigationRepositoryImpl):NavigationRepository
}