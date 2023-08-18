package com.danielwaiguru.touristnews.domain.models

data class Tourist(
    val createdAt: String,
    val id: Int,
    val touristEmail: String,
    val touristLocation: String,
    val touristName: String
)
