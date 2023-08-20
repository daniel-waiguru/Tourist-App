package com.danielwaiguru.touristnews.presentation.tourists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.danielwaiguru.touristnews.designsystem.components.OctoKitProgressIndicator
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.presentation.common.ErrorView
import com.danielwaiguru.touristnews.presentation.common.PagingLoadingView
import com.danielwaiguru.touristnews.presentation.tourists.details.TouristDetailDialog

@Composable
fun TouristsScreen(
    viewModel: TouristsViewModel = hiltViewModel()
) {
    val uiState = viewModel.touristsUIState.collectAsLazyPagingItems()
    var showDetails by remember {
        mutableStateOf(Pair(false, -1))
    }
    TouristsScreen(
        uiState = uiState,
        showDetails = showDetails,
        onClick = {
            showDetails = it
        }
    )
}

@Composable
fun TouristsScreen(
    uiState: LazyPagingItems<Tourist>,
    showDetails: Pair<Boolean, Int>,
    onClick: (Pair<Boolean, Int>) -> Unit
) {
    val isLoading = uiState.loadState.source.refresh is LoadState.Loading && uiState.itemCount == 0
    val errorMessage = (uiState.loadState.source.refresh as? LoadState.Error)?.error?.message
    val hasError = uiState.loadState.source.refresh is LoadState.Error && !errorMessage.isNullOrBlank()
    if (showDetails.first) {
        TouristDetailDialog(
            touristId = showDetails.second,
            onDismiss = { onClick(Pair(false, -1)) }
        )
    }
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
        TouristsContent(
            modifier = Modifier
                .fillMaxSize()
                .testTag("tourist_list"),
            tourists = uiState,
            onClick = { onClick(Pair(true, it)) }
        )
    }
}

@Composable
private fun TouristsContent(
    modifier: Modifier = Modifier,
    tourists: LazyPagingItems<Tourist>,
    onClick: (touristId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = tourists.itemCount,
            key = tourists.itemKey { it.id }
        ) { position ->
            tourists[position]?.let { article ->
                TouristItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onClick(article.id) },
                    tourist = article
                )
            }
        }
        item {
            val appendLoadState = tourists.loadState.append
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
                    tourists.retry()
                }
            )
        }
    }
}