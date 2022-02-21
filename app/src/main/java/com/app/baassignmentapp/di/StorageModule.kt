package com.app.baassignmentapp.di

import com.app.baassignmentapp.model.local.database.AppDB
import com.app.baassignmentapp.model.local.storage.StorageImpl
import com.app.baassignmentapp.model.local.storage.StorageIntf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StorageModule {
    @Singleton
    @Provides
    fun provideStorage(appDB: AppDB) : StorageIntf = StorageImpl(appDB)
}