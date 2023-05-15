package com.example.livefrontproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.navigation.Screens2.navToNewsDetail
import com.example.livefrontproject.network.NetworkingSingleton
import com.example.livefrontproject.ui.compose.NewsDetailComposable
import com.example.livefrontproject.ui.compose.NewsListComposable
import java.lang.IllegalArgumentException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Composable defining start destination and parameters for application
 */
@Composable
fun MainApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens2.NEWS_LIST) {
        newsListGraph(
            modifier = modifier,
            onNewsItemClick = { newsItem ->
                val encodedNewsItem = newsItem.encodeUrl()
                navController.navToNewsDetail(encodedNewsItem)
            }
        )
        newsDetailGraph(modifier = modifier)
    }
}

private object Screens2 {

    /**
     * Route for [newsListGraph]
     */
    const val NEWS_LIST = "news_list"

    /**
     * Route for [newsDetailGraph]
     */
    const val NEWS_DETAIL = "news_detail/{newsItem}"

    /**
     * Used to handle navigation arguments to [newsDetailGraph]
     */
    fun NavController.navToNewsDetail(newsItem: ArticleItem) {
        this.navigate("news_detail/${newsItem.toJsonString()}")
    }
}

private fun NavGraphBuilder.newsListGraph(
    modifier: Modifier,
    onNewsItemClick: (ArticleItem) -> Unit
) {
    composable(route = Screens2.NEWS_LIST) {
        NewsListComposable(modifier = modifier, onNewsItemClick = onNewsItemClick)
    }
}

private fun NavGraphBuilder.newsDetailGraph(modifier: Modifier) {
    composable(
        route = Screens2.NEWS_DETAIL,
        arguments = listOf(navArgument("newsItem") { type = NavType.StringType })
    ) { backStackEntry ->
        val newsItem = backStackEntry.arguments?.getString("newsItem")?.toNewsItem()
            ?: throw IllegalArgumentException("Must pass 'newsItem' argument")
        NewsDetailComposable(modifier = modifier, newsItem = newsItem)
    }
}

private fun String.toNewsItem(): ArticleItem =
    NetworkingSingleton.AppJson.decodeFromString(ArticleItem.serializer(), this)

private fun ArticleItem.toJsonString(): String =
    NetworkingSingleton.AppJson.encodeToString(ArticleItem.serializer(), this)

fun ArticleItem.encodeUrl(): ArticleItem {
    val encodedUrl = URLEncoder.encode(this.urlToImage, StandardCharsets.UTF_8.toString())
    return this.copy(urlToImage = encodedUrl)
}