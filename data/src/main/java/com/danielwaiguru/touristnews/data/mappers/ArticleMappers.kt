package com.danielwaiguru.touristnews.data.mappers

import com.danielwaiguru.touristnews.data.models.dtos.ArticleDto
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.danielwaiguru.touristnews.domain.models.Article

internal fun ArticleDto.toArticleEntity(): ArticleEntity =
    ArticleEntity(
        commentCount = commentCount,
        createdAt = createdAt,
        description = description,
        id = id,
        location = location,
        multiMedia = multiMedia.map(ArticleDto.MultiMediaDto::toMultiMediaEntity),
        title = title,
        user = user.toUserEntity()
    )

internal fun ArticleDto.UserDto.toUserEntity(): ArticleEntity.UserEntity =
    ArticleEntity.UserEntity(
        name = name, profilePicture = profilePicture, userId = userId
    )

internal fun ArticleDto.MultiMediaDto.toMultiMediaEntity(): ArticleEntity.MultiMediaEntity =
    ArticleEntity.MultiMediaEntity(
        createdAt = createdAt,
        description = description,
        id = id,
        name = name,
        title = title,
        url = url
    )

internal fun ArticleEntity.toArticle(): Article =
    Article(
        createdAt = createdAt,
        description = description,
        id = id,
        location = location,
        title = title,
        user = user.toUser()
    )

internal fun ArticleEntity.UserEntity.toUser() : Article.User =
    Article.User(name, profilePicture)