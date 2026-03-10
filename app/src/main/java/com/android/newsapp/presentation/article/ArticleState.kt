package com.android.newsapp.presentation.article

import com.android.newsapp.domain.model.Article

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val page: Int = 1,
    val isInitialLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false
)