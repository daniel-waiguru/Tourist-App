package com.danielwaiguru.touristnews.database.daos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.danielwaiguru.touristnews.database.base.BaseDbTest
import com.danielwaiguru.touristnews.database.test_data.testNewsArticle
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class ArticleDaoTest : BaseDbTest() {
    private lateinit var articleDao: ArticleDao

    @Before
    fun setUp() {
        articleDao = db.articleDao()
    }

    @Test
    fun articleDao_gets_correct_next_page_for_pagination() {
        val articleEntities = listOf(
            testNewsArticle(),
            testNewsArticle(
                id = 1,
                createdAt = "2022-02-05T13:19:57.4227967",
                nextPage = 2
            )
        )
        runTest {
            articleDao.saveArticles(articleEntities)
            val nextPage = articleDao.getNextPage()
            assertNotNull(nextPage)
            Truth.assertThat(nextPage)
                .isEqualTo(2)
        }
    }

    @Test
    fun articleDao_clearAll_empties_the_table() {
        val articleEntities = listOf(
            testNewsArticle(),
            testNewsArticle(
                id = 1,
                createdAt = "2022-02-05T13:19:57.4227967",
                nextPage = 2
            )
        )
        runTest {
            articleDao.saveArticles(articleEntities)
            articleDao.clearAll()
            val nextPage = articleDao.getNextPage()
            assertNull(nextPage)
        }
    }
}