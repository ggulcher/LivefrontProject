package com.example.livefrontproject.ui.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.livefrontproject.model.ArticleItem

/**
 * Composable for list item of a single [ArticleItem]
 */
@Composable
fun DetailListItem(
    newsItem: ArticleItem
) {

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = newsItem.title.toString(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        DetailProperty(label = "Author", value = newsItem.author.toString())
        DetailProperty(label = "Published", value = newsItem.publishedAt.toString())
        DetailProperty(label = "Description", value = newsItem.description.toString())
    }
}

/**
 * Items in [DetailListItem] displaying a single value
 */
@Composable
fun DetailProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Visible,
        )
    }
}
