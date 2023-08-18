package com.danielwaiguru.touristnews.data.sources.local

import androidx.paging.PagingSource
import com.danielwaiguru.touristnews.database.daos.ArticleDao
import com.danielwaiguru.touristnews.database.daos.TouristDao
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.danielwaiguru.touristnews.database.entities.TouristEntity
import javax.inject.Inject

internal interface LocalDataSource {
    suspend fun saveArticles(articles: List<ArticleEntity>)

    fun getCachedArticles(): PagingSource<Int, ArticleEntity>

    suspend fun saveTourists(tourists: List<TouristEntity>)

    fun getCachedTourists(): PagingSource<Int, TouristEntity>
}

internal class RoomDataSource @Inject constructor(
    private val articleDao: ArticleDao,
    private val touristDao: TouristDao
): LocalDataSource {
    override suspend fun saveArticles(articles: List<ArticleEntity>) =
        articleDao.saveArticles(articles)

    override fun getCachedArticles(): PagingSource<Int, ArticleEntity> =
        articleDao.getCachedArticles()

    override suspend fun saveTourists(tourists: List<TouristEntity>) =
        touristDao.saveTourists(tourists)

    override fun getCachedTourists(): PagingSource<Int, TouristEntity> =
        touristDao.getCachedTourists()
}