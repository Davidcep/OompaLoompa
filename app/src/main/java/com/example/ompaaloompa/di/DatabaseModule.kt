package com.example.ompaaloompa.di

import android.content.Context
import com.example.ompaaloompa.data.local.AppDatabase
import com.example.ompaaloompa.data.local.OompaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideOompaDao(appDatabase: AppDatabase): OompaDao {
        return appDatabase.oompaDao()
    }

}