package com.android.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.domain.model.Article
import com.android.newsapp.domain.repository.ArticleRepository
import com.android.newsapp.domain.util.Resource
import com.android.newsapp.presentation.article.ArticleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: ArticleRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ArticleState())
    val uiState: StateFlow<ArticleState> = _state

    private val _selectedArticleId = MutableStateFlow<String?>(null)
    val selectedArticle: StateFlow<Article?> = _selectedArticleId
        .combine(_state) { id, state -> state.articles.find { it.id == id } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val pageSize = 10
    private var lastFetchTime = 0L
    private val fetchDebounceMs = 300L

    init {
        fetchNews()
    }


    fun fetchNews(refresh: Boolean = false) {
        val now = System.currentTimeMillis()
        if (now - lastFetchTime < fetchDebounceMs) return
        lastFetchTime = now

        val state = _state.value
        if (state.isInitialLoading || state.isPaginationLoading) return

        val page = if (refresh) 1 else state.page

        viewModelScope.launch {
            if (page == 1) {
                _state.update { it.copy(isInitialLoading = true, error = null) }
            } else {
                _state.update { it.copy(isPaginationLoading = true) }
            }

            when (val result = repository.getNews(page, pageSize)) {
                is Resource.Success -> {
                    val newArticles = result.data?.articles ?: emptyList()
                    _state.update {
                        val updatedArticles =
                            if (refresh || page == 1) newArticles
                            else it.articles + newArticles
                        it.copy(
                            articles = updatedArticles,
                            page = page + 1,
                            isInitialLoading = false,
                            isPaginationLoading = false,
                            endReached = newArticles.isEmpty(),
                            error = null
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isInitialLoading = false,
                            isPaginationLoading = false,
                            error = result.message
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            isInitialLoading = false,
                            isPaginationLoading = true,
                            error = null
                        )
                    }
                }
            }
        }
    }

    //Set selected article for detail screen
    fun selectArticle(articleId: String) {
        _selectedArticleId.value = articleId
    }


}