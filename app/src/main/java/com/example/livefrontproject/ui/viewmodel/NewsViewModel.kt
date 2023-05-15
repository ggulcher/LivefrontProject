package com.example.livefrontproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    /**
     * Internal mutable state value
     */
    private val _state = MutableStateFlow(NewsState.INITIAL)

    /**
     * Publisher of [NewsState]
     */
    val state: StateFlow<NewsState> = _state

    init {
        getNewsItems()
    }

    private fun getNewsItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.getNewsItems()
                    _state.update { currentState ->
                        currentState.copy(newsItems = response)
                    }
                } catch (e: Exception) {
                    throw NullPointerException()
                }
            }
        }
    }

    /**
     * To be invoked when valid [query] is entered to perform custom request
     */
    fun getSearchedItems(query: String) {
        viewModelScope.launch {
            val searched = repository.searchNews(query)
            _state.update { currentState ->
                currentState.copy(
                    searchQuery = query,
                    searchItems = searched
                )
            }
        }
    }

    data class NewsState(
        val searchQuery: String,
        val newsItems: List<ArticleItem>,
        val searchItems: List<ArticleItem>,
    ) {

        val articles: List<ArticleItem> = when {
            searchQuery.isEmpty() -> newsItems
            else -> searchItems
        }

        companion object {
            val INITIAL = NewsState(
                searchQuery = "",
                newsItems = emptyList(),
                searchItems = emptyList(),
            )
        }
    }
}
