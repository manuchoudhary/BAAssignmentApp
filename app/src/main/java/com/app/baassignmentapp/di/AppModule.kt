package com.app.baassignmentapp.di

import com.app.baassignmentapp.datasource.MainInteractor
import com.app.baassignmentapp.datasource.repo.ArticleRepository
import com.app.baassignmentapp.model.local.storage.StorageIntf
import com.app.baassignmentapp.model.remote.BAApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun getApiClient(retrofit: Retrofit) : BAApiService =
        retrofit.create(BAApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(BAApiService: BAApiService, storageIntf: StorageIntf) =
        ArticleRepository(BAApiService, storageIntf)

    @Singleton
    @Provides
    fun providesInteractor(articleRepository: ArticleRepository) = MainInteractor(articleRepository)
}