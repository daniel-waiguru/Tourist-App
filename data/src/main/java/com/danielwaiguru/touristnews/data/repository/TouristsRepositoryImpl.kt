package com.danielwaiguru.touristnews.data.repository

import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import javax.inject.Inject

internal class TouristsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): TouristsRepository {
}