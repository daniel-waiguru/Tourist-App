package com.danielwaiguru.touristnews.domain.repositories

import androidx.paging.PagingData
import com.danielwaiguru.touristnews.domain.models.Tourist
import kotlinx.coroutines.flow.Flow

interface TouristsRepository {
    fun getTourists(): Flow<PagingData<Tourist>>
}