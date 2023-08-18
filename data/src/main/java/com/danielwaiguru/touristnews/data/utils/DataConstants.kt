package com.danielwaiguru.touristnews.data.utils

import androidx.paging.PagingConfig

object DataConstants {
    const val DEFAULT_PAGE = 1
    const val PAGE_SIZE = 30

    fun getPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )
}