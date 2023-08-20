package com.danielwaiguru.touristnews.testing.test_data

import com.danielwaiguru.touristnews.domain.models.Tourist

fun testTourist(
    id: Int = 0,
    createdAt: String = "2022-02-09T13:19:57.4227967"
) = Tourist(
    createdAt = createdAt,
    id = id,
    touristEmail = "tourist$id@test.com",
    touristLocation = "Nairobi",
    touristName = "Daniel$id"
)