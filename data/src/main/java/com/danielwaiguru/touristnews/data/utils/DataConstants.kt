package com.danielwaiguru.touristnews.data.utils

import androidx.paging.PagingConfig

object DataConstants {
    const val DEFAULT_PAGE = 1
    private const val PAGE_SIZE = 10

    fun getPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )
}