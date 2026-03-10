package com.android.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.android.newsapp.domain.repository.ArticleRepository

class NewsViewModel(
    private val repository: ArticleRepository,
): ViewModel() {
}