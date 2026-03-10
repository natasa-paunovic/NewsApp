package com.android.newsapp.presentation.article.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.newsapp.domain.model.Article
import com.android.newsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel
import com.android.newsapp.presentation.article.components.*

@Composable
fun ArticleListScreen(
    onArticleClick: (Article) -> Unit,
    viewModel: NewsViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()

    // Infinite scroll triggers fetchNews() when near bottom
    InfiniteScrollEffect(
        listState = listState,
        buffer = 3,
        onLoadMore = { viewModel.fetchNews() }
    )

    // Show error snackbar for retry
    LaunchedEffect(uiState.error) {
        uiState.error?.let { msg ->
            snackbarHostState.showSnackbar(msg)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        PullToRefreshBox(
            isRefreshing = uiState.isInitialLoading,
            onRefresh = { viewModel.fetchNews(refresh = true) },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when {
                uiState.isInitialLoading && uiState.articles.isEmpty() -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                uiState.articles.isEmpty() -> {
                    Text(
                        "No articles found",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
                else -> {
                    ArticleList(
                        articles = uiState.articles,
                        isPaginationLoading = uiState.isPaginationLoading,
                        endReached = uiState.endReached,
                        listState = listState,
                        onArticleClick = onArticleClick
                    )
                }
            }
        }
    }
}