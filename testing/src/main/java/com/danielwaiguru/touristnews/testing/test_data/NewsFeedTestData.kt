package com.danielwaiguru.touristnews.testing.test_data

import com.danielwaiguru.touristnews.domain.models.Article

val authorUser = Article.User(
    name = "Happy Singh24",
    profilePicture = "https://www.adequatetravel.com/ATMultimedia/UserProfileCover/7059/2bc2d9ae-4bc0-445a-852f-e3b49d05c553.png"
)
fun testNewsArticle(
    id: Int = 0,
    createdAt: String = "2022-02-09T13:19:57.4227967"
) = Article(
    createdAt = createdAt,
    description = "Traval Live\\r\\n",
    location = "Asheville, NC, USA",
    user = authorUser,
    id = id,
    title = "Las Vegas, NV, USA $id",
    url = "https://www.adequatetravel.com/ATMultimedia/UserProfileCover/7059/2bc2d9ae-4bc0-445a-852f-e3b49d05c553.png"
)