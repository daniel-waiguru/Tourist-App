package com.danielwaiguru.touristnews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.danielwaiguru.touristnews.data.mappers.toArticle
import com.danielwaiguru.touristnews.data.paging.ArticlesRemoteMediator
import com.danielwaiguru.touristnews.data.sources.local.LocalDataSource
import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.data.utils.DataConstants.getPagingConfig
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.domain.repositories.NewsFeedRepository
import com.danielwaiguru.touristnews.domain.utils.Dispatcher
import com.danielwaiguru.touristnews.domain.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class NewsFeedRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher
) : NewsFeedRepository {
    override fun getNewsArticles(): Flow<PagingData<Article>> =
        Pager(
            config = getPagingConfig(),
            remoteMediator = ArticlesRemoteMediator(localDataSource, remoteDataSource)
        ) {
            localDataSource.getCachedArticles()
        }.flow.map { pagingData ->
            pagingData.map { entity -> entity.toArticle() }
        }.flowOn(ioDispatcher)
}