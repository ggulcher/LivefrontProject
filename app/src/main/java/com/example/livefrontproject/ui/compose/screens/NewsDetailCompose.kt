package com.example.livefrontproject.ui.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.ui.compose.components.DetailListItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailComposable(
    modifier: Modifier,
    newsItem: ArticleItem
) {
    Scaffold(modifier = modifier) {
        Column() {
            AsyncImage(model = newsItem.urlToImage, contentDescription = "")
            Box(modifier = Modifier.fillMaxSize()) {
                DetailListItem(newsItem)
            }
        }
    }
}
