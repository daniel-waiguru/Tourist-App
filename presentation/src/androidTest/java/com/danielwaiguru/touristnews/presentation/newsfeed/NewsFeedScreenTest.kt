package com.danielwaiguru.touristnews.presentation.newsfeed

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.testing.dummy_data.notFoundErrorMessage
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class NewsFeedScreenTest {
    @get:Rule
    val rule = createComposeRule()
    private val loadingLoadState by lazy {
        flowOf(
            PagingData.empty<Article>(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Loading,
                    prepend = LoadState.Loading,
                    append = LoadState.Loading
                )
            )
        )
    }
    private val errorLoadState by lazy {
        flowOf(
            PagingData.empty<Article>(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(Throwable(notFoundErrorMessage)),
                    prepend = LoadState.Error(Throwable(notFoundErrorMessage)),
                    append = LoadState.Error(Throwable(notFoundErrorMessage))
                )
            )
        )
    }

    @Test
    fun loading_state_is_handled_by_displaying_progress_indicator() {
        rule.setContent {
            NewsFeedScreen(
                uiState = loadingLoadState.collectAsLazyPagingItems()
            )
        }
        rule.onNodeWithTag("octokit_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun error_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            NewsFeedScreen(
                uiState = errorLoadState.collectAsLazyPagingItems()
            )
        }
        rule.onNodeWithTag("error_view").assertIsDisplayed()
    }
}