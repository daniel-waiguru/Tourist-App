package com.danielwaiguru.touristnews.presentation.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.danielwaiguru.touristnews.designsystem.components.OctoKitProgressIndicator
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.presentation.common.ErrorView
import com.danielwaiguru.touristnews.presentation.common.PagingLoadingView

@Composable
fun NewFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val uiState = viewModel.newsFeedUIState.collectAsLazyPagingItems()
    val isLoading = uiState.loadState.source.refresh is LoadState.Loading && uiState.itemCount == 0
    val errorMessage = (uiState.loadState.source.refresh as? LoadState.Error)?.error?.message
    val hasError = uiState.loadState.source.refresh is LoadState.Error && !errorMessage.isNullOrBlank()
    if (isLoading) {
        OctoKitProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    } else if (hasError) {
        ErrorView(
            modifier = Modifier
                .fillMaxSize(),
            description = errorMessage
        )
    } else {
        NewsFeedContent(
            modifier = Modifier
                .fillMaxSize(),
            feeds = uiState
        )
    }
}

@Composable
private fun NewsFeedContent(
    modifier: Modifier = Modifier,
    feeds: LazyPagingItems<Article>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = feeds.itemCount,
            key = feeds.itemKey { it.id }
        ) { position ->
            feeds[position]?.let { article ->
                NewFeedItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    article = article
                )
            }
        }
        item {
            val appendLoadState = feeds.loadState.append
            val errorMessage by remember(appendLoadState) {
                derivedStateOf {
                    if (appendLoadState is LoadState.Error) {
                        appendLoadState.error.message
                    } else {
                        null
                    }
                }
            }
            PagingLoadingView(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                isLoading = appendLoadState is LoadState.Loading,
                error = errorMessage,
                onRetry = {
                    feeds.retry()
                }
            )
        }
    }
}