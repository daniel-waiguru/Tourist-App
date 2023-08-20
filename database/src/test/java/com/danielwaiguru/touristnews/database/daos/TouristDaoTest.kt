package com.danielwaiguru.touristnews.database.daos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.danielwaiguru.touristnews.database.base.BaseDbTest
import com.danielwaiguru.touristnews.database.test_data.testTourist
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
class TouristDaoTest: BaseDbTest() {
    private lateinit var touristDao: TouristDao
    private val touristEntities = listOf(
        testTourist(),
        testTourist(
            id = 1,
            createdAt = "2022-02-05T13:19:57.4227967",
            nextPage = 3
        )
    )

    @Before
    fun setUp() {
        touristDao = db.touristDao()
    }

    @Test
    fun touristDao_gets_correct_next_page_for_pagination() {
        runTest {
            touristDao.saveTourists(touristEntities)
            val nextPage = touristDao.getNextPage()
            assertNotNull(nextPage)
            Truth.assertThat(nextPage)
                .isEqualTo(3)
        }
    }
    @Test
    fun touristDao_clearAll_empties_the_table() = runTest {
        touristDao.saveTourists(touristEntities)
        touristDao.clearAll()
        val nextPage = touristDao.getNextPage()
        assertNull(nextPage)
    }
}