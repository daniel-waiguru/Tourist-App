package com.danielwaiguru.touristnews.domain.use_cases

import com.google.common.truth.Truth
import org.junit.Test

class DateFormatterUseCaseTest {
    @Test
    fun dateFormatterUseCase_returns_correctly_formatted_date() {
        // Given
        val jsonDate = "2022-02-09T13:19:57.4227967"
        val expectedDate = "9 Feb, 2022"
        val dateFormatterUseCase = DateFormatterUseCase()

        // When
        val formattedDate = dateFormatterUseCase(jsonDate = jsonDate)

        // Then
        Truth.assertThat(formattedDate).isEqualTo(expectedDate)
    }
}