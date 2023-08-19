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
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
internal class TouristsRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, TouristEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TouristEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    localDataSource.getTouristsNextPage() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
            }
            val pageNumber = loadKey ?: DataConstants.DEFAULT_PAGE
            val response = remoteDataSource.getTourists(pageNumber)
            val isLastPage = response.page >= response.totalPages
            val nextPage = if (isLastPage) null else response.page + 1
            localDataSource.dbTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearTourists()
                }
                val touristEntities = response.data.map(TouristDto::toTouristEntity)
                    .map { it.copy(nextPage = nextPage) }
                localDataSource.saveTourists(touristEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = isLastPage
            )
        } catch (e: Exception) {
            /**
             * Propagate the error and maybe Log to Crashlytics/Sentry
             */
            Timber.e(e)
            MediatorResult.Error(e)
        }
    }
}