package com.danielwaiguru.touristnews.data.models.responses

import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.squareup.moshi.Json

data class TouristsResponse(
    val `data`: List<TouristDto>,
    val page: Int,
    @field:Json(name = "per_page")
    val perPage: Int,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "totalrecord")
    val totalRecord: Int
)