package com.example.livefrontproject.ui.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.magnifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.livefrontproject.R
import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.ui.compose.components.ErrorDialog
import com.example.livefrontproject.ui.compose.components.LoadingDialog
import com.example.livefrontproject.ui.compose.components.NewsListItem
import com.example.livefrontproject.ui.compose.components.SearchBar
import com.example.livefrontproject.ui.viewmodel.NewsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListComposable(
    modifier: Modifier,
    onNewsItemClick: (ArticleItem) -> Unit
) {

    val defaultSearchText = stringResource(id = R.string.default_search_text)
    val searchState = remember { mutableStateOf(TextFieldValue("")) }
    val lazyListState = rememberLazyListState()
    val viewModel: NewsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val query = searchState.value.text

    Scaffold(
        modifier = modifier,
        content = {
            if (state.value.status == NewsViewModel.UiStatus.Error) {
                ErrorDialog(
                    modifier = Modifier.fillMaxWidth(),
                    onClickRetry = { viewModel.onRetryClick() }
                )
            } else {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (state.value.status == NewsViewModel.UiStatus.Loading) {
                        LoadingDialog()
                    }
                    SearchBar(
                        hint = defaultSearchText,
                        modifier = Modifier,
                        state = searchState,
                        query = query,
                        onSearchClick = { viewModel.getSearchedItems(query) }
                    )
                    LazyColumn(
                        state = lazyListState,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(items = state.value.articles) {
                            NewsListItem(
                                newsItem = it,
                                onNewsItemClick = onNewsItemClick
                            )
                        }
                    }
                }
            }
        }
    )
}
