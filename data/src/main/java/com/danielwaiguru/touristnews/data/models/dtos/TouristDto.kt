package com.danielwaiguru.touristnews.data.models.dtos

import com.squareup.moshi.Json

data class TouristDto(
    @field:Json(name = "createdat")
    val createdAt: String,
    val id: Int,
    @field:Json(name = "tourist_email")
    val touristEmail: String,
    @field:Json(name = "tourist_location")
    val touristLocation: String,
    @field:Json(name = "tourist_name")
    val touristName: String?
)