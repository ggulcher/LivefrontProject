package com.example.livefrontproject.network

import com.example.livefrontproject.network.model.NewsApiResponse
import com.example.livefrontproject.util.Constants.API_KEY
import com.example.livefrontproject.util.Constants.DEFAULT_COUNTRY_CODE
import com.example.livefrontproject.util.Constants.DEFAULT_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsItems(
        @Query("country") countryCode: String = DEFAULT_COUNTRY_CODE,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsApiResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsApiResponse
}
