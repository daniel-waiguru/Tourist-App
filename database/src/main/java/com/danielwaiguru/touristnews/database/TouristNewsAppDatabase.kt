package com.danielwaiguru.touristnews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielwaiguru.touristnews.database.daos.ArticleDao
import com.danielwaiguru.touristnews.database.daos.TouristDao
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.danielwaiguru.touristnews.database.entities.TouristEntity

@Database(
    entities = [
        ArticleEntity::class,
        TouristEntity::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class TouristNewsAppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    abstract fun touristDao(): TouristDao

    companion object {
        const val DB_NAME = "tourists_news_app_databse"
    }
}