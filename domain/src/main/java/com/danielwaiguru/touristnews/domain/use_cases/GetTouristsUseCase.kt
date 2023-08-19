package com.danielwaiguru.touristnews.domain.use_cases

import androidx.paging.map
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import kotlinx.coroutines.flow.map

class GetTouristsUseCase(
    private val touristsRepository: TouristsRepository,
    private val dateFormatterUseCase: DateFormatterUseCase
) {
    operator fun invoke() = touristsRepository
        .getTourists()
        .map { pagingData ->
            pagingData.map { tourist ->
                tourist.copy(
                    createdAt = dateFormatterUseCase(jsonDate = tourist.createdAt)
                )
            }
        }
}