package com.danielwaiguru.touristnews.domain.models

data class Article(
    val createdAt: String?,
    val description: String?,
    val id: Int,
    val location: String,
    val title: String?,
    val user: User,
    val url: String?
) {
    data class User(
        val name: String,
        val profilePicture: String?
    )
}
