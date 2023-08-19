package com.danielwaiguru.touristnews.data.repository

import com.danielwaiguru.touristnews.data.models.dtos.TouristDto
import com.danielwaiguru.touristnews.data.sources.local.LocalDataSource
import com.danielwaiguru.touristnews.data.sources.remote.RemoteDataSource
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import com.danielwaiguru.touristnews.testing.dummy_data.notFoundErrorMessage
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class TouristsRepositoryTest {
    @MockK
    val localDataSource = mockk<LocalDataSource>(relaxed = true)

    @MockK
    val remoteDataSource = mockk<RemoteDataSource>(relaxed = true)
    private lateinit var touristRepository: TouristsRepository

    @Before
    fun setup() {
        touristRepository = TouristsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            ioDispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `test getTouristById correctly maps success response`() {
        coEvery {
            remoteDataSource.getTouristById(any())
        } returns fakeTouristSuccessResponse

        runTest {
            val response = touristRepository.getTouristById(1)
            Truth.assertThat(response).isInstanceOf(ResultWrapper.Success::class.java)
        }
    }

    @Test
    fun `test getTouristById correctly maps error response`() {
        coEvery {
            remoteDataSource.getTouristById(any())
        } answers {
            throw HttpException(
                Response.error<TouristDto>(
                    404,
                    "{\"Message\":\"$notFoundErrorMessage\"}"
                        .toResponseBody("application/json".toMediaType())
                )
            )
        }

        runTest {
            val response = touristRepository.getTouristById(1)
            Truth.assertThat(response).isInstanceOf(ResultWrapper.Error::class.java)
        }
    }
}

val fakeTouristSuccessResponse = TouristDto(
    createdAt = "2022-02-03T13:58:15.5720217",
    id = 12,
    touristName = "Daniel",
    touristEmail = "tourist@test.com",
    touristLocation = "Nairobi"
)