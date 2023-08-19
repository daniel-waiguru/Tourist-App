package com.danielwaiguru.touristnews.data.sources.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.danielwaiguru.touristnews.database.TouristNewsAppDatabase
import com.danielwaiguru.touristnews.database.daos.ArticleDao
import com.danielwaiguru.touristnews.database.daos.TouristDao
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.danielwaiguru.touristnews.database.entities.TouristEntity
import javax.inject.Inject

interface LocalDataSource {
    suspend fun saveArticles(articles: List<ArticleEntity>)

    suspend fun clearArticles()

    fun getCachedArticles(): PagingSource<Int, ArticleEntity>

    suspend fun getNextArticlePage(): Int?

    suspend fun getTouristsNextPage(): Int?

    suspend fun clearTourists()

    suspend fun saveTourists(tourists: List<TouristEntity>)

    fun getCachedTourists(): PagingSource<Int, TouristEntity>

    suspend fun dbTransaction(onTransaction: suspend () -> Unit)
}

internal class RoomDataSource @Inject constructor(
    private val articleDao: ArticleDao,
    private val touristDao: TouristDao,
    private val database: TouristNewsAppDatabase
) : LocalDataSource {
    override suspend fun saveArticles(articles: List<ArticleEntity>) =
        articleDao.saveArticles(articles)

    override suspend fun clearArticles() = articleDao.clearAll()

    override fun getCachedArticles(): PagingSource<Int, ArticleEntity> =
        articleDao.getCachedArticles()

    override suspend fun getNextArticlePage(): Int? = articleDao.getNextPage()

    override suspend fun getTouristsNextPage(): Int? = touristDao.getNextPage()

    override suspend fun clearTourists() = touristDao.clearAll()

    override suspend fun saveTourists(tourists: List<TouristEntity>) =
        touristDao.saveTourists(tourists)

    override fun getCachedTourists(): PagingSource<Int, TouristEntity> =
        touristDao.getCachedTourists()

    override suspend fun dbTransaction(onTransaction: suspend () -> Unit) {
        database.withTransaction {
            onTransaction()
        }
    }
}