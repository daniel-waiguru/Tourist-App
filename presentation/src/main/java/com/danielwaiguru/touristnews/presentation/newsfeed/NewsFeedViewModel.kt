package com.danielwaiguru.touristnews.presentation.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.danielwaiguru.touristnews.domain.use_cases.GetNewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    getNewsFeedUseCase: GetNewsFeedUseCase
) : ViewModel() {
    val newsFeedUIState = getNewsFeedUseCase()
        .cachedIn(viewModelScope)
}