package com.danielwaiguru.touristnews.data.models.dtos

import com.squareup.moshi.Json

data class TouristDto(
    @Json(name = "createdat")
    val createdAt: String,
    val id: Int,
    @Json(name = "tourist_email")
    val touristEmail: String,
    @Json(name = "tourist_location")
    val touristLocation: String,
    @Json(name = "tourist_name")
    val touristName: String
)