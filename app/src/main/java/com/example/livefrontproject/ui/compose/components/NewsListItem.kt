package com.example.livefrontproject.ui.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.livefrontproject.model.ArticleItem

/**
 * A single item from list of [ArticleItem]
 *
 * [onNewsItemClick] passed back to navigation for handling
 */
@Composable
fun NewsListItem(
    newsItem: ArticleItem,
    onNewsItemClick: (ArticleItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp))
            .height(intrinsicSize = IntrinsicSize.Max)
            .clickable(onClick = {
                onNewsItemClick.invoke(newsItem)
            })
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            model = newsItem.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.None
        )
        Column(modifier = Modifier
            .padding(8.dp)
            .weight(1f)) {
            Text(
                modifier = Modifier.testTag("news_item_title"),
                text = newsItem.title ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = newsItem.author ?: ""
            )
        }
    }
}
