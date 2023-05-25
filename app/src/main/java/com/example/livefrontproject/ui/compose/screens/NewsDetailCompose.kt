package com.example.livefrontproject.ui.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val scrollState = rememberScrollState()
    Scaffold(modifier = modifier) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = newsItem.urlToImage,
                contentDescription = ""
            )
            Box(modifier = Modifier.fillMaxSize()) {
                DetailListItem(newsItem)
            }
        }
    }
}
