package com.ruichaoqun.core.datastore

import android.app.Application
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideGson():Gson = Gson()

    @Singleton
    @Provides
    fun provideDataStoreDataSource(@ApplicationContext context: Application,gson: Gson):LocalDataSource = DataStoreDataSource(context,gson)

}