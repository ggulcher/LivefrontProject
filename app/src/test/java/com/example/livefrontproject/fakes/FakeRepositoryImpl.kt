package com.example.livefrontproject.fakes

import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.repository.NewsRepository

class FakeRepositoryImpl: NewsRepository {

    private val testList = listOf(
        ArticleItem(title = "Test Article 1"),
        ArticleItem(title = "Test Article 2")
    )

    private val testSearch = listOf(
        ArticleItem(title = "Test Search 1"),
        ArticleItem(title = "Test Search 2")
    )

    override suspend fun getNewsItems(): List<ArticleItem> {
        return testList
    }

    override suspend fun searchNews(query: String): List<ArticleItem> {
        return testSearch
    }
}
