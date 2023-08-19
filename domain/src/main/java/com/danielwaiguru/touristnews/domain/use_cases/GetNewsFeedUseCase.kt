package com.danielwaiguru.touristnews.domain.use_cases

import androidx.paging.PagingData
import androidx.paging.map
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.domain.repositories.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNewsFeedUseCase(
    private val dateFormatterUseCase: DateFormatterUseCase,
    private val newsFeedRepository: NewsFeedRepository
) {
    operator fun invoke(): Flow<PagingData<Article>> = newsFeedRepository.getNewsArticles()
        .map { pagingData ->
            pagingData.map { article ->
                article.copy(
                    createdAt = dateFormatterUseCase(
                        jsonDate = article.createdAt
                    )
                )
            }
        }
}