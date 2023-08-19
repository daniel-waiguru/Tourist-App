package com.danielwaiguru.touristnews.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.touristnews.presentation.utils.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val tabs = TabItem.values()
    val pagerState = rememberPagerState()
    Column(
        modifier = modifier
    ) {
        TabsSection(
            tabs = tabs,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        )
        TabsContent(
            tabs = tabs,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabsSection(
    modifier: Modifier = Modifier,
    tabs: Array<TabItem>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                },
                text = {
                    Text(text = stringResource(tabItem.title))
                },
                icon = {
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabsContent(
    tabs: Array<TabItem>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        tabs[page].screen()
    }
}