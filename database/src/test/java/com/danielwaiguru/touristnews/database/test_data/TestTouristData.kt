package com.danielwaiguru.touristnews.database.test_data

import com.danielwaiguru.touristnews.database.entities.TouristEntity

fun testTourist(
    id: Int = 0,
    createdAt: String = "2022-02-09T13:19:57.4227967",
    nextPage: Int = 2
) = TouristEntity(
    createdAt = createdAt,
    id = id,
    nextPage = nextPage,
    touristEmail = "tourist@test.com",
    touristLocation = "Nairobi",
    touristName = "Daniel"
)