package com.danielwaiguru.touristnews.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.danielwaiguru.touristnews.data.mappers.toArticleEntity
import com.danielwaiguru.touristnews.data.models.dtos.ArticleDto
import com.danielwaiguru.touristnews.data.sources.local.LocalDataSource
import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.data.utils.DataConstants.DEFAULT_PAGE
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
internal class ArticlesRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    localDataSource.getNextArticlePage() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
            }
            val pageNumber = loadKey ?: DEFAULT_PAGE
            val response = remoteDataSource.getNewsFeed(pageNumber = pageNumber)
            val isLastPage = response.page >= response.totalPages
            val nextPage = if (isLastPage) null else response.page + 1

            localDataSource.dbTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearArticles()
                }
                val articledEntities = response.data
                    .map(ArticleDto::toArticleEntity)
                    .map { it.copy(nextPage = nextPage) }
                localDataSource.saveArticles(articledEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = isLastPage
            )
        } catch (e: Exception) {
            Timber.e(e)
            /**
             * Propagate the error and maybe Log to Crashlytics/Sentry
             */
            MediatorResult.Error(e)
        }
    }
}