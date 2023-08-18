package com.danielwaiguru.touristnews.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.danielwaiguru.touristnews.data.sources.remote.api.TouristNewsApiService
import com.danielwaiguru.touristnews.data.sources.remote.interceptors.HeadersInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn
internal object NetworkingModule  {
    private const val REQUEST_TIMEOUT_S = 20L
    @Singleton
    @Provides
    internal fun provideHttpClient(
        @ApplicationContext appContext: Context
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        val chuckerInterceptor = ChuckerInterceptor.Builder(appContext)
            .collector(ChuckerCollector(appContext))
            .maxContentLength(250_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()
        return OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT_S, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT_S, TimeUnit.SECONDS)
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(logging)
            .addInterceptor(chuckerInterceptor)
            .dispatcher(
                Dispatcher().apply {
                    maxRequestsPerHost = 8
                }
            )
            .build()
    }
    @Singleton
    @Provides
    internal fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()
    @Singleton
    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    internal fun apiServiceBuilder(retrofit: Retrofit): TouristNewsApiService {
        return retrofit.create(TouristNewsApiService::class.java)
    }
}