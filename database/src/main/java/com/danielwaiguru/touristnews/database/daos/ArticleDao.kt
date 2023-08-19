package com.danielwaiguru.touristnews.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.danielwaiguru.touristnews.database.entities.ArticleEntity.Companion.ARTICLES_TABLED_NAME

/**
 *Data access object for [ArticleEntity] entity
 */
@Dao
interface ArticleDao {
    /**
     * Save articles
     *
     * @param articles[ArticleEntity]
     */
    @Upsert
    suspend fun saveArticles(articles: List<ArticleEntity>)

    /**
     * Get cached articles
     *
     * @return
     */
    @Query("SELECT * FROM $ARTICLES_TABLED_NAME ORDER BY id DESC")
    fun getCachedArticles(): PagingSource<Int, ArticleEntity>

    /**
     * Clear all [ArticleEntity]
     *
     */
    @Query("DELETE FROM $ARTICLES_TABLED_NAME")
    suspend fun clearAll()

    @Query("SELECT Max(nextPage) FROM $ARTICLES_TABLED_NAME")
    suspend fun getNextPage(): Int?
}