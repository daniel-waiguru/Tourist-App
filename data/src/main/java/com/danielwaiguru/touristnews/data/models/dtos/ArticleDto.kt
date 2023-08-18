package com.danielwaiguru.touristnews.data.models.dtos

import com.squareup.moshi.Json

data class ArticleDto(
    val commentCount: Int,
    @field:Json(name = "createdat")
    val createdAt: String,
    val description: String,
    val id: Int,
    val location: String,
    val multiMedia: List<MultiMediaDto>,
    val title: String,
    val user: UserDto
) {
    data class MultiMediaDto(
        @field:Json(name = "createat")
        val createdAt: String,
        val description: String?,
        val id: Int,
        val name: String,
        val title: String?,
        val url: String
    )
    data class UserDto(
        val name: String,
        @field:Json(name = "profilepicture")
        val profilePicture: String,
        @field:Json(name = "userid")
        val userId: Int
    )
}