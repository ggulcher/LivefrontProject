package com.example.livefrontproject.network.model

import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    data class Article(
        val url: String? = "",
        val author: String? = "",
        val content: String? = "",
        val description: String? = "",
        val publishedAt: String? = "",
        val source: Source? = Source("", ""),
        val title: String? = "",
        val urlToImage: String? = "",
        val isSaved: Boolean = false
    ) {
        data class Source(
            val id: String,
            val name: String
        )
    }
}
