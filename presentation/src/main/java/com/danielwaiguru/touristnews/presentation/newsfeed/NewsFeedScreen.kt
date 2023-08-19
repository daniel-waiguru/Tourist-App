package com.danielwaiguru.touristnews.presentation.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.presentation.common.PagingLoadingView

@Composable
fun NewFeedScreen(
    viewModel: ViewModel
) {
//    NewFeedItem(
//        modifier = Modifier.fillMaxSize(),
//        article =
//    )
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
            key = feeds.itemKey { Article::id }
        ) { position ->
            feeds[position]?.let { article ->
                NewFeedItem(
                    modifier = Modifier.fillMaxWidth(),
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
                    .padding(5.dp)
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