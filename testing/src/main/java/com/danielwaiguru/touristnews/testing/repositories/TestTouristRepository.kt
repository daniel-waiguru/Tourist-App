package com.danielwaiguru.touristnews.testing.repositories

import androidx.paging.PagingData
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import com.danielwaiguru.touristnews.testing.test_data.testTourist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class TestTouristRepository : TouristsRepository {
    private val touristsResult = MutableStateFlow<ResultWrapper<Tourist>>(
        ResultWrapper.Success(testTourist())
    )
    override fun getTourists(): Flow<PagingData<Tourist>> =
        flowOf(PagingData.from(listOf(testTourist())))

    override suspend fun getTouristById(touristId: Int): ResultWrapper<Tourist> =
        touristsResult.value
    fun setTouristResult(result: ResultWrapper<Tourist>) {
        touristsResult.update { result }
    }
}
