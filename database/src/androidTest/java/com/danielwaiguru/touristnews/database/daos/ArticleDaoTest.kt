package com.danielwaiguru.touristnews.database.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danielwaiguru.touristnews.database.TouristNewsAppDatabase
import com.danielwaiguru.touristnews.database.test_data.testNewsArticle
import org.junit.Before
import org.junit.Test

class ArticleDaoTest {
    private lateinit var articleDao: ArticleDao
    private lateinit var db: TouristNewsAppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            TouristNewsAppDatabase::class.java
        ).build()
        articleDao = db.articleDao()
    }

    @Test
    fun articleDao_saves_items_list() {
        val articleEntities = listOf(
            testNewsArticle(),
            testNewsArticle(
                id = 1,
                createdAt = "2022-02-05T13:19:57.4227967"
            )
        )
        val articles = articleDao.getCachedArticles()
    }
}