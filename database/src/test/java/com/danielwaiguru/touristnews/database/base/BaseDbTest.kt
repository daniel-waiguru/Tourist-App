package com.danielwaiguru.touristnews.database.base

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danielwaiguru.touristnews.database.TouristNewsAppDatabase
import com.danielwaiguru.touristnews.testing.utils.MainCoroutineRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseDbTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()
    lateinit var db: TouristNewsAppDatabase
    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TouristNewsAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    @After
    open fun tearDownDb() {
        db.clearAllTables()
        db.close()
    }
}