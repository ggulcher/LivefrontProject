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
            try {
                val response = repository.getNewsItems()
                _state.update { currentState ->
                    currentState.copy(newsItems = response, status = UiStatus.Data)
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(status = UiStatus.Error)
                }
            }
        }
    }

    /**
     * To be invoked during a network error and a new request is needed
     */
    fun onRetryClick() {
        _state.update { currentState ->
            currentState.copy(status = UiStatus.Loading)
        }
        getNewsItems()
    }

    /**
     * To be invoked when valid [query] is entered to perform custom request
     */
    fun getSearchedItems(query: String) {
        viewModelScope.launch {
            try {
                val searched = repository.searchNews(query)
                _state.update { currentState ->
                    currentState.copy(
                        searchQuery = query,
                        searchItems = searched
                    )
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(status = UiStatus.Error)
                }
            }
        }
    }

    data class NewsState(
        val searchQuery: String,
        val newsItems: List<ArticleItem>,
        val searchItems: List<ArticleItem>,
        val status: UiStatus
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
                status = UiStatus.Loading
            )
        }
    }

    /**
     * Represents the current status of the UI within [NewsState]
     */
    enum class UiStatus {
        Loading,
        Data,
        Error
    }
}
