package com.danielwaiguru.touristnews.data.di

import com.danielwaiguru.touristnews.data.repository.NewsFeedRepositoryImpl
import com.danielwaiguru.touristnews.data.repository.TouristsRepositoryImpl
import com.danielwaiguru.touristnews.domain.repositories.NewsFeedRepository
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Singleton
    @Binds
    internal abstract fun bindNewsFeedRepository(
        newsFeedRepositoryImpl: NewsFeedRepositoryImpl
    ): NewsFeedRepository

    @Singleton
    @Binds
    internal abstract fun bindTouristsRepository(
        touristsRepositoryImpl: TouristsRepositoryImpl
    ): TouristsRepository
}