package com.example.livefrontproject.di

import com.example.livefrontproject.network.ApiService
import com.example.livefrontproject.repository.NewsRepository
import com.example.livefrontproject.repository.NewsRepositoryImpl
import com.example.livefrontproject.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(
        api: ApiService
    ): NewsRepository {
        return NewsRepositoryImpl(api)
    }
}
