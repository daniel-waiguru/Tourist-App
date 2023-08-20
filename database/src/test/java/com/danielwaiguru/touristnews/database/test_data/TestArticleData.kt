package com.danielwaiguru.touristnews.database.test_data

import com.danielwaiguru.touristnews.database.entities.ArticleEntity

val authorUser = ArticleEntity.UserEntity(
    name = "Happy Singh24",
    userId = 7059,
    profilePicture = "https://www.adequatetravel.com/ATMultimedia/UserProfileCover/7059/2bc2d9ae-4bc0-445a-852f-e3b49d05c553.png"
)
fun testNewsArticle(
    id: Int = 0,
    createdAt: String = "2022-02-09T13:19:57.4227967",
    nextPage: Int = 2
) = ArticleEntity(
    commentCount = 1,
    createdAt = createdAt,
    description = "Traval Live\\r\\n",
    location = "Asheville, NC, USA",
    multiMedia = emptyList(),
    user = authorUser,
    id = id,
    title = "Las Vegas, NV, USA",
    nextPage = nextPage
)
