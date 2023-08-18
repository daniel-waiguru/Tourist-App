package com.danielwaiguru.touristnews.domain.repositories

import androidx.paging.PagingData
import com.danielwaiguru.touristnews.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsFeedRepository {
    fun getNewsArticles(): Flow<PagingData<Article>>
}