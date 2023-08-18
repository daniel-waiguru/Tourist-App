package com.danielwaiguru.touristnews.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.danielwaiguru.touristnews.data.mappers.toTouristEntity
import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.danielwaiguru.touristnews.data.sources.local.LocalDataSource
import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.data.utils.DataConstants
import com.danielwaiguru.touristnews.database.entities.TouristEntity

@OptIn(ExperimentalPagingApi::class)
internal class TouristsRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): RemoteMediator<Int, TouristEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TouristEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.id
                }
            }
            val pageNumber = loadKey ?: DataConstants.DEFAULT_PAGE
            val response = remoteDataSource.getTourists(pageNumber)
            val touristEntities = response.data.map(TouristDto::toTouristEntity)
            localDataSource.saveTourists(touristEntities)
            val isLastPage = response.page >= response.totalPages
            MediatorResult.Success(
                endOfPaginationReached = isLastPage
            )
        } catch (e: Exception) {
            /**
             * Propagate the error and maybe Log to Crashlytics/Sentry
             */
            MediatorResult.Error(e)
        }
    }
}