package com.example.livefrontproject.model

import com.example.livefrontproject.network.model.NewsApiResponse
import kotlinx.serialization.Serializable

/**
 * A separate data class to capture list items from [NewsApiResponse]
 *
 * Used to pass items to the through the repository and to the ViewModel
 *
 * This practice helps decouple the data from the data layer and reduce
 * exposure of data objects interacting with API
 */
@Serializable
data class ArticleItem(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val title: String? = "",
    val urlToImage: String? = ""
)
