package com.danielwaiguru.touristnews.data.repository

import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.domain.repositories.NewsFeedRepository
import javax.inject.Inject

internal class NewsFeedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): NewsFeedRepository {
}