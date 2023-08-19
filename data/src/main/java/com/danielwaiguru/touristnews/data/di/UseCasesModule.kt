package com.danielwaiguru.touristnews.data.di

import com.danielwaiguru.touristnews.domain.repositories.NewsFeedRepository
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import com.danielwaiguru.touristnews.domain.use_cases.DateFormatterUseCase
import com.danielwaiguru.touristnews.domain.use_cases.GetNewsFeedUseCase
import com.danielwaiguru.touristnews.domain.use_cases.GetTouristsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {
    @ViewModelScoped
    @Provides
    fun provideDateFormatterUseCase(): DateFormatterUseCase = DateFormatterUseCase()

    @ViewModelScoped
    @Provides
    fun provideGetNewsFeedUseCase(
        dateFormatterUseCase: DateFormatterUseCase,
        newsFeedRepository: NewsFeedRepository
    ): GetNewsFeedUseCase = GetNewsFeedUseCase(dateFormatterUseCase, newsFeedRepository)

    @ViewModelScoped
    @Provides
    fun provideGetTouristsUseCase(
        dateFormatterUseCase: DateFormatterUseCase,
        touristsRepository: TouristsRepository
    ): GetTouristsUseCase = GetTouristsUseCase(touristsRepository, dateFormatterUseCase)
}