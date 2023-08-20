package com.danielwaiguru.touristnews.data.utils

import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

data class ErrorResponse(
    @field:Json(name = "Message")
    val message: String
)
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            if (throwable is CancellationException) {
                throw throwable
            }
            return@withContext when (this) {
                is HttpException -> {
                    val code = this.code()
                    val errorResponse = parseErrorBody(this)
                    ResultWrapper.Error(
                        code = code,
                        errorMessage = errorResponse?.message
                    )
                }

                else -> {
                    ResultWrapper.Error(
                        errorMessage = throwable.message
                    )
                }
            }
        }
    }
}

fun parseErrorBody(throwable: HttpException): ErrorResponse? = try {
    throwable.response()?.errorBody()?.toString()?.let {
        val moshi = Moshi.Builder()
            .build()
        val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
        jsonAdapter.fromJson(it)
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}
