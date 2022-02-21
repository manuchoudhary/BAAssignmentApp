package com.app.baassignmentapp.di

import android.content.Context
import com.app.baassignmentapp.model.local.database.AppDB
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGSON() : Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesAppDB(@ApplicationContext context: Context) : AppDB {
        return AppDB.getInstance(context)
    }
}