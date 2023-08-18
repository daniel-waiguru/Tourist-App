package com.danielwaiguru.touristnews.data.models.responses

import com.danielwaiguru.touristnews.data.models.dtos.ArticleDto
import com.squareup.moshi.Json

data class NewsFeedResponse(
    val `data`: List<ArticleDto>,
    val page: Int,
    @field:Json(name = "per_page")
    val perPage: Int,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "totalrecord")
    val totalRecord: Int
)