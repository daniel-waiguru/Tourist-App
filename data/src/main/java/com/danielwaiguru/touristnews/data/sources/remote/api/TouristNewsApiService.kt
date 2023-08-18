package com.danielwaiguru.touristnews.data.sources.remote.api

import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.danielwaiguru.touristnews.data.models.responses.NewsFeedResponse
import com.danielwaiguru.touristnews.data.models.responses.TouristsResponse
import com.danielwaiguru.touristnews.data.sources.remote.utils.ApiEndpoints.NEWS_FEED_ENDPOINT
import com.danielwaiguru.touristnews.data.sources.remote.utils.ApiEndpoints.TOURIST_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TouristNewsApiService {
    @GET(NEWS_FEED_ENDPOINT)
    suspend fun getNewsFeed(
        @Query("page") pageNumber: Int
    ): NewsFeedResponse

    @GET(TOURIST_ENDPOINT)
    suspend fun getTourists(
        @Query("page") pageNumber: Int
    ): TouristsResponse

    @GET("$TOURIST_ENDPOINT/{touristId}")
    suspend fun getTouristById(
        @Path("touristId") touristId: Int
    ): TouristDto
}