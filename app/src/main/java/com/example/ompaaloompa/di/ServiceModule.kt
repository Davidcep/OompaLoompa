package com.example.ompaaloompa.di

import com.example.ompaaloompa.data.remote.OompaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideOompaService(): OompaService {
        return OompaService.create()
    }

}