package com.danielwaiguru.touristnews.data.sources.remote

import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.danielwaiguru.touristnews.data.models.responses.NewsFeedResponse
import com.danielwaiguru.touristnews.data.models.responses.TouristsResponse
import com.danielwaiguru.touristnews.data.sources.remote.api.TouristNewsApiService
import javax.inject.Inject

internal interface RemoteDataSource {
    suspend fun getNewsFeed(pageNumber: Int): NewsFeedResponse

    suspend fun getTourists(pageNumber: Int): TouristsResponse

    suspend fun getTouristById(touristId: Int): TouristDto
}

internal class RetrofitDataSource @Inject constructor(
    private val apiService: TouristNewsApiService
) : RemoteDataSource {
    override suspend fun getNewsFeed(pageNumber: Int): NewsFeedResponse =
        apiService.getNewsFeed(pageNumber)

    override suspend fun getTourists(pageNumber: Int): TouristsResponse =
        apiService.getTourists(pageNumber)

    override suspend fun getTouristById(touristId: Int): TouristDto =
        apiService.getTouristById(touristId)
}