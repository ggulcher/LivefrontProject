package com.example.livefrontproject.repository

import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.network.ApiService
import com.example.livefrontproject.network.model.NewsApiResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: ApiService
): NewsRepository {

    override suspend fun getNewsItems(): List<ArticleItem> {
        val response = api.getNewsItems()
        return response.toModel()
    }

    override suspend fun searchNews(query: String): List<ArticleItem> {
        val response = api.searchNews(query)
        return response.toModel()
    }

    /**
     * Part of the decoupling process
     *
     * This extension function will return a list of [ArticleItem] from fetched data
     */
    private fun NewsApiResponse.toModel(): List<ArticleItem> {
        return this.articles.map {
            ArticleItem(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                title = it.title,
                urlToImage = it.urlToImage
            )
        }
    }
}
