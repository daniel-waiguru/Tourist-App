@file:OptIn(ExperimentalPagingApi::class)

package com.danielwaiguru.touristnews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.danielwaiguru.touristnews.data.mappers.toTourist
import com.danielwaiguru.touristnews.data.paging.TouristsRemoteMediator
import com.danielwaiguru.touristnews.data.sources.local.LocalDataSource
import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.data.utils.DataConstants.getPagingConfig
import com.danielwaiguru.touristnews.data.utils.safeApiCall
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import com.danielwaiguru.touristnews.domain.utils.Dispatcher
import com.danielwaiguru.touristnews.domain.utils.DispatcherProvider
import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TouristsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher
) : TouristsRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getTourists(): Flow<PagingData<Tourist>> =
        Pager(
            config = getPagingConfig(),
            remoteMediator = TouristsRemoteMediator(localDataSource, remoteDataSource)
        ) {
            localDataSource.getCachedTourists()
        }.flow.map { pagingData ->
            pagingData.map { entity -> entity.toTourist() }
        }.flowOn(ioDispatcher)

    override suspend fun getTouristById(touristId: Int): ResultWrapper<Tourist> =
        safeApiCall(ioDispatcher) {
            remoteDataSource.getTouristById(touristId).toTourist()
        }
}