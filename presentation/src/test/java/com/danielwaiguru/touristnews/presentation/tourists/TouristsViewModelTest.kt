package com.danielwaiguru.touristnews.presentation.tourists

import com.danielwaiguru.touristnews.domain.use_cases.GetTouristsUseCase
import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import com.danielwaiguru.touristnews.testing.base.BaseViewModelTest
import com.danielwaiguru.touristnews.testing.repositories.TestTouristRepository
import com.danielwaiguru.touristnews.testing.repositories.testTourist
import com.google.common.truth.Truth
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TouristsViewModelTest : BaseViewModelTest() {
    private val touristsRepository = TestTouristRepository()

    @MockK
    val getTouristsUseCase = mockk<GetTouristsUseCase>(relaxed = true)
    private lateinit var viewModel: TouristsViewModel

    @Before
    fun setup() {
        viewModel = TouristsViewModel(
            getTouristsUseCase = getTouristsUseCase,
            touristsRepository = touristsRepository
        )
    }

    @Test
    fun `test initially touristUIState is default state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.touristUIState.collect()
        }
        val touristUIState = viewModel.touristUIState.value
        assertFalse(touristUIState.isLoading)
        assertNull(touristUIState.errorMessage)
        assertEquals(touristUIState, TouristUIState())
        collectJob.cancel()
    }

    @Test
    fun `test handling loading state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.touristUIState.collect()
        }
        touristsRepository.setTouristResult(ResultWrapper.Loading)
        viewModel.getTouristById(1)
        val touristUIState = viewModel.touristUIState.value
        assertTrue(touristUIState.isLoading)
        assertNull(touristUIState.errorMessage)
        collectJob.cancel()
    }

    @Test
    fun `test handling success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.touristUIState.collect()
        }
        viewModel.getTouristById(1)
        val touristUIState = viewModel.touristUIState.value
        assertFalse(touristUIState.isLoading)
        assertNull(touristUIState.errorMessage)
        Truth.assertThat(touristUIState.tourist).isEqualTo(testTourist())
        collectJob.cancel()
    }

    @Test
    fun `test handling error state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.touristUIState.collect()
        }
        touristsRepository.setTouristResult(ResultWrapper.Error(errorMessage = "Tourist not found"))
        viewModel.getTouristById(1)
        val touristUIState = viewModel.touristUIState.value
        assertFalse(touristUIState.isLoading)
        assertNotNull(touristUIState.errorMessage)
        assertNull(touristUIState.tourist)
        collectJob.cancel()
    }
}