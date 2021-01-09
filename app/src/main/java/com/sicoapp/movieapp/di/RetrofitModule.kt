package com.sicoapp.movieapp.di

import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author ll4
 * @date 1/3/2021
 */

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(logger).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServisFlow =
        retrofit.create(ApiServisFlow::class.java)

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): ApiServiceFlowable =
        retrofit.create(ApiServiceFlowable::class.java)
}
