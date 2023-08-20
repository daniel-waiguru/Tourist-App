package com.danielwaiguru.touristnews.presentation.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.danielwaiguru.touristnews.presentation.R
import com.danielwaiguru.touristnews.presentation.newsfeed.NewsFeedScreen
import com.danielwaiguru.touristnews.presentation.tourists.TouristsScreen

typealias ScreenComposable = @Composable () -> Unit
enum class TabItem(@StringRes val title: Int, val screen: ScreenComposable) {
    NewsFeed(
        title = R.string.articles_title,
        screen = { NewsFeedScreen() }
    ), Tourists(
        title = R.string.tourists_title,
        screen = { TouristsScreen() }
    );
}
