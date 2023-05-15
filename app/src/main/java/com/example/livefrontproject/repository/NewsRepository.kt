package com.example.livefrontproject.repository

import com.example.livefrontproject.model.ArticleItem

interface NewsRepository {

    /**
     * Returns a list of trending [ArticleItem]
     */
    suspend fun getNewsItems(): List<ArticleItem>

    /**
     * Returns a list of searched [ArticleItem] by passing a query to the GET request
     */
    suspend fun searchNews(query: String): List<ArticleItem>
}
