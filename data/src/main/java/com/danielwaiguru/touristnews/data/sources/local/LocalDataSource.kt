package com.danielwaiguru.touristnews.data.sources.local

import com.danielwaiguru.touristnews.database.daos.ArticleDao
import com.danielwaiguru.touristnews.database.daos.TouristDao
import javax.inject.Inject

internal interface LocalDataSource {
}

internal class RoomDataSource @Inject constructor(
    private val articleDao: ArticleDao,
    private val touristDao: TouristDao
): LocalDataSource {

}