package com.danielwaiguru.touristnews.presentation.tourists

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.testing.dummy_data.notFoundErrorMessage
import com.danielwaiguru.touristnews.testing.test_data.testTourist
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class TouristsScreenTest {
    @get:Rule
    val rule = createComposeRule()
    private val loadingLoadState by lazy {
        flowOf(
            PagingData.empty<Tourist>(
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
            PagingData.empty<Tourist>(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(Throwable(notFoundErrorMessage)),
                    prepend = LoadState.Error(Throwable(notFoundErrorMessage)),
                    append = LoadState.Error(Throwable(notFoundErrorMessage))
                )
            )
        )
    }
    private val successLoadState by lazy {
        flowOf(
            PagingData.from(
                listOf(
                    testTourist(),
                    testTourist(id = 1)
                )
            )
        )
    }
    private val appendingLoadState by lazy {
        flowOf(
            PagingData.from(
                listOf(
                    testTourist(),
                    testTourist(id = 1)
                ),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                    append = LoadState.Loading
                )
            )
        )
    }

    @Test
    fun loading_state_is_handled_by_displaying_progress_indicator() {
        rule.setContent {
            TouristsScreen(
                uiState = loadingLoadState.collectAsLazyPagingItems(),
                showDetails = Pair(false, -1),
                onClick = {}
            )
        }
        rule.onNodeWithTag("octokit_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun error_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            TouristsScreen(
                uiState = errorLoadState.collectAsLazyPagingItems(),
                showDetails = Pair(false, -1),
                onClick = {}
            )
        }
        rule.onNodeWithTag("error_view").assertIsDisplayed()
    }

    @Test
    fun success_state_is_handled_by_displaying_tourist_list() {
        rule.setContent {
            TouristsScreen(
                uiState = successLoadState.collectAsLazyPagingItems(),
                showDetails = Pair(false, -1),
                onClick = {}
            )
        }
        rule.onNodeWithTag("tourist_list").assertIsDisplayed()
    }

    @Test
    fun paging_data_is_displayed_when_there_is_data() {
        rule.setContent {
            TouristsScreen(
                uiState = successLoadState.collectAsLazyPagingItems(),
                showDetails = Pair(false, -1),
                onClick = {}
            )
        }
        rule.onNodeWithText(testTourist().touristName!!).assertIsDisplayed()
        rule.onAllNodesWithText(testTourist().touristLocation).assertCountEquals(2)
    }

    @Test
    fun pagingLoading_whenAppendingItems_showPagingLoadingView() {
        rule.setContent {
            TouristsScreen(
                uiState = appendingLoadState.collectAsLazyPagingItems(),
                showDetails = Pair(false, -1),
                onClick = {}
            )
        }
        rule.onNodeWithTag("paging_loading_view").assertIsDisplayed()
    }
}