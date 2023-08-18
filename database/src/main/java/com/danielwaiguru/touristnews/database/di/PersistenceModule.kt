package com.danielwaiguru.touristnews.database.di

import android.content.Context
import androidx.room.Room
import com.danielwaiguru.touristnews.database.TouristNewsAppDatabase
import com.danielwaiguru.touristnews.database.TouristNewsAppDatabase.Companion.DB_NAME
import com.danielwaiguru.touristnews.database.daos.ArticleDao
import com.danielwaiguru.touristnews.database.daos.TouristDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Persistence module
 *
 * @constructor Create empty Persistence module
 */
@Module
@InstallIn(SingletonComponent::class)
internal object PersistenceModule {
    @Provides
    @Singleton
    internal fun provideTouristNewsAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            TouristNewsAppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideTouristDao(database: TouristNewsAppDatabase): TouristDao = database.touristDao()

    @Provides
    @Singleton
    fun provideArticleDao(database: TouristNewsAppDatabase): ArticleDao = database.articleDao()
}