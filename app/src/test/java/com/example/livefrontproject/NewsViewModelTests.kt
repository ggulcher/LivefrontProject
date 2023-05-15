package com.example.livefrontproject

import com.example.livefrontproject.fakes.FakeRepositoryImpl
import com.example.livefrontproject.model.ArticleItem
import com.example.livefrontproject.ui.viewmodel.NewsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class NewsViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var viewModel: NewsViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = NewsViewModel(FakeRepositoryImpl())
    }

    @Test
    fun testReceiveArticlesOnInit() {
        // ViewModel init block calls getNewsItems(), no need to call here
        assertNotNull(viewModel.state.value.newsItems)
    }

    @Test
    fun testUpdateSearchQuery() {

        val searchQuery = "search"

        viewModel.getSearchedItems(searchQuery)

        val result = viewModel.state.value.searchItems

        assertNotNull(result)
        assertEquals(result, viewModel.state.value.articles)
    }
}
